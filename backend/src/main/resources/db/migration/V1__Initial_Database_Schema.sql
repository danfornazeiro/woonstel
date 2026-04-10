-- Flyway Migration: V1__Initial_Database_Schema.sql
-- Sistema de Gestão Técnica de Condomínios
-- Date: 2026-04-07

-- =============================================
-- 1. TABELAS BASE - USER (Inheritance)
-- =============================================

CREATE TABLE user_tb (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) UNIQUE,
    role VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_user_email ON user_tb(email);
CREATE INDEX idx_user_cpf ON user_tb(cpf);

-- =============================================
-- 2. CONDOMINIUM (Tabela Central)
-- =============================================

CREATE TABLE condominium_table (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(200) NOT NULL,
    photo_url VARCHAR(500),
    address VARCHAR(300),
    city VARCHAR(100),
    state VARCHAR(2),
    zip_code VARCHAR(10),
    phone VARCHAR(20),
    people_in INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_condominium_name ON condominium_table(name);

-- =============================================
-- 3. RESIDENT (Morador)
-- =============================================

CREATE TABLE resident_tb (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL UNIQUE,
    condominium_id UUID NOT NULL,
    apartment INTEGER NOT NULL,
    block VARCHAR(10),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user_tb(id) ON DELETE CASCADE,
    FOREIGN KEY (condominium_id) REFERENCES condominium_table(id) ON DELETE CASCADE
);

CREATE INDEX idx_resident_condominium ON resident_tb(condominium_id);
CREATE INDEX idx_resident_apartment ON resident_tb(apartment);
CREATE UNIQUE INDEX idx_resident_apt_block_cond ON resident_tb(apartment, block, condominium_id);

-- =============================================
-- 4. MANAGER (Síndico)
-- =============================================

CREATE TABLE manager_tb (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL UNIQUE,
    condominium_id UUID NOT NULL,
    start_mandate DATE,
    final_mandate DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user_tb(id) ON DELETE CASCADE,
    FOREIGN KEY (condominium_id) REFERENCES condominium_table(id) ON DELETE CASCADE
);

CREATE INDEX idx_manager_condominium ON manager_tb(condominium_id);

-- =============================================
-- 5. EMPLOYEE (Funcionário)
-- =============================================

CREATE TABLE employee_tb (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL UNIQUE,
    condominium_id UUID NOT NULL,
    role_function VARCHAR(100),
    salary NUMERIC(10, 2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user_tb(id) ON DELETE CASCADE,
    FOREIGN KEY (condominium_id) REFERENCES condominium_table(id) ON DELETE CASCADE
);

CREATE INDEX idx_employee_condominium ON employee_tb(condominium_id);

-- =============================================
-- 6. COMMON_AREA (Áreas Comuns)
-- =============================================

CREATE TABLE commom_area_tb (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    condominium_id UUID NOT NULL,
    name VARCHAR(150) NOT NULL,
    description VARCHAR(500),
    status VARCHAR(50),
    status_common_area VARCHAR(50),
    price NUMERIC(10, 2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (condominium_id) REFERENCES condominium_table(id) ON DELETE CASCADE
);

CREATE INDEX idx_common_area_condominium ON commom_area_tb(condominium_id);

-- =============================================
-- 7. SOLICITATION (Problemas Reportados)
-- =============================================

CREATE TABLE request_tb (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    resident_id UUID NOT NULL,
    condominium_id UUID NOT NULL,
    description VARCHAR(1000) NOT NULL,
    category VARCHAR(100),
    location VARCHAR(200),
    photo_url VARCHAR(500),
    status VARCHAR(50) DEFAULT 'OPEN',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (resident_id) REFERENCES resident_tb(id) ON DELETE CASCADE,
    FOREIGN KEY (condominium_id) REFERENCES condominium_table(id) ON DELETE CASCADE
);

CREATE INDEX idx_solicitation_resident ON request_tb(resident_id);
CREATE INDEX idx_solicitation_condominium ON request_tb(condominium_id);
CREATE INDEX idx_solicitation_status ON request_tb(status);
CREATE INDEX idx_solicitation_created ON request_tb(created_at);

-- =============================================
-- 8. TASK (Tarefas de Manutenção)
-- =============================================

CREATE TABLE task_tb (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    solicitation_id UUID,
    employee_id UUID,
    condominium_id UUID NOT NULL,
    description VARCHAR(1000) NOT NULL,
    type VARCHAR(100),
    image_url VARCHAR(500),
    priority VARCHAR(50) DEFAULT 'MEDIUM',
    status VARCHAR(50) DEFAULT 'OPEN',
    budget NUMERIC(10, 2),
    actual_cost NUMERIC(10, 2),
    deadline TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (solicitation_id) REFERENCES request_tb(id) ON DELETE SET NULL,
    FOREIGN KEY (employee_id) REFERENCES employee_tb(id) ON DELETE SET NULL,
    FOREIGN KEY (condominium_id) REFERENCES condominium_table(id) ON DELETE CASCADE
);

CREATE INDEX idx_task_solicitation ON task_tb(solicitation_id);
CREATE INDEX idx_task_employee ON task_tb(employee_id);
CREATE INDEX idx_task_condominium ON task_tb(condominium_id);
CREATE INDEX idx_task_status ON task_tb(status);
CREATE INDEX idx_task_priority ON task_tb(priority);

-- =============================================
-- 9. RESERVATION (Reservas de Áreas)
-- =============================================

CREATE TABLE reservation_tb (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    resident_id UUID NOT NULL,
    area_id UUID NOT NULL,
    condominium_id UUID NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    status VARCHAR(50) DEFAULT 'PENDING',
    notes VARCHAR(500),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (resident_id) REFERENCES resident_tb(id) ON DELETE CASCADE,
    FOREIGN KEY (area_id) REFERENCES commom_area_tb(id) ON DELETE CASCADE,
    FOREIGN KEY (condominium_id) REFERENCES condominium_table(id) ON DELETE CASCADE
);

CREATE INDEX idx_reservation_resident ON reservation_tb(resident_id);
CREATE INDEX idx_reservation_area ON reservation_tb(area_id);
CREATE INDEX idx_reservation_condominium ON reservation_tb(condominium_id);
CREATE INDEX idx_reservation_time ON reservation_tb(start_time, end_time);

-- =============================================
-- 10. THIRD_PARTY_COMPANY (Empresas Terceirizadas)
-- =============================================

CREATE TABLE third_party_company_tb (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    condominium_id UUID NOT NULL,
    name VARCHAR(200) NOT NULL,
    cnpj VARCHAR(18) UNIQUE,
    contact_person VARCHAR(150),
    phone VARCHAR(20),
    email VARCHAR(150),
    website VARCHAR(300),
    address VARCHAR(300),
    service_type VARCHAR(100),
    description VARCHAR(1000),
    average_rating NUMERIC(3, 1),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (condominium_id) REFERENCES condominium_table(id) ON DELETE CASCADE
);

CREATE INDEX idx_company_condominium ON third_party_company_tb(condominium_id);
CREATE INDEX idx_company_service_type ON third_party_company_tb(service_type);

-- =============================================
-- 11. SCHEDULED_SERVICE (Serviços Agendados)
-- =============================================

CREATE TABLE scheduled_service_tb (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    condominium_id UUID NOT NULL,
    third_party_company_id UUID,
    description VARCHAR(1000),
    scheduled_date TIMESTAMP,
    service_type VARCHAR(100),
    status VARCHAR(50) DEFAULT 'SCHEDULED',
    estimated_cost NUMERIC(10, 2),
    actual_cost NUMERIC(10, 2),
    observations VARCHAR(1000),
    completed_at TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (condominium_id) REFERENCES condominium_table(id) ON DELETE CASCADE,
    FOREIGN KEY (third_party_company_id) REFERENCES third_party_company_tb(id) ON DELETE SET NULL
);

CREATE INDEX idx_scheduled_service_condominium ON scheduled_service_tb(condominium_id);
CREATE INDEX idx_scheduled_service_company ON scheduled_service_tb(third_party_company_id);
CREATE INDEX idx_scheduled_service_date ON scheduled_service_tb(scheduled_date);

-- =============================================
-- 12. CONDOMIUM_INVITATION_LINK (Links de Convite)
-- =============================================

CREATE TABLE condomium_invitation_link_tb (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    condominium_id UUID NOT NULL,
    manager_id UUID NOT NULL,
    invitation_code VARCHAR(255) UNIQUE NOT NULL,
    invitation_url VARCHAR(500),
    is_active BOOLEAN DEFAULT TRUE,
    max_uses INTEGER,
    current_uses INTEGER DEFAULT 0,
    notes VARCHAR(500),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    expires_at TIMESTAMP,
    deactivated_at TIMESTAMP,
    FOREIGN KEY (condominium_id) REFERENCES condominium_table(id) ON DELETE CASCADE,
    FOREIGN KEY (manager_id) REFERENCES manager_tb(id) ON DELETE CASCADE
);

CREATE INDEX idx_invitation_condominium ON condomium_invitation_link_tb(condominium_id);
CREATE INDEX idx_invitation_code ON condomium_invitation_link_tb(invitation_code);
CREATE INDEX idx_invitation_active ON condomium_invitation_link_tb(is_active);

-- =============================================
-- 13. DASHBOARD_METRICS (Métricas do Dashboard)
-- =============================================

CREATE TABLE dashboard_metrics_tb (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    condominium_id UUID NOT NULL UNIQUE,
    open_problems BIGINT DEFAULT 0,
    tasks_in_progress BIGINT DEFAULT 0,
    completed_tasks BIGINT DEFAULT 0,
    high_priority_tasks BIGINT DEFAULT 0,
    total_estimated_cost NUMERIC(15, 2) DEFAULT 0,
    total_actual_cost NUMERIC(15, 2) DEFAULT 0,
    scheduled_services_this_week BIGINT DEFAULT 0,
    confirmed_reservations BIGINT DEFAULT 0,
    completion_rate NUMERIC(5, 2) DEFAULT 0,
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (condominium_id) REFERENCES condominium_table(id) ON DELETE CASCADE
);

-- =============================================
-- CONSTRAINTS E COMENTÁRIOS
-- =============================================

-- Comentários para documentação (PostgreSQL)
COMMENT ON TABLE condominium_table IS 'Tabela central que representa cada condomínio no sistema';
COMMENT ON TABLE user_tb IS 'Tabela base para autenticação (herança compartilhada)';
COMMENT ON TABLE resident_tb IS 'Moradores do condomínio';
COMMENT ON TABLE manager_tb IS 'Síndicos responsáveis pelos condomínios';
COMMENT ON TABLE employee_tb IS 'Funcionários do condomínio';
COMMENT ON TABLE request_tb IS 'Problemas técnicos reportados pelos moradores';
COMMENT ON TABLE task_tb IS 'Tarefas de manutenção criadas a partir de solicitations';
COMMENT ON TABLE condomium_invitation_link_tb IS 'Links únicos para convitar moradores a se cadastrarem no sistema';

-- Fim da migração

