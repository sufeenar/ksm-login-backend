 

CREATE TABLE IF NOT EXISTS public.k_smart_user
(
    id uuid NOT NULL,
    phone_number character varying(64) COLLATE pg_catalog."default",
    tenant_id integer,
    name character varying(255) COLLATE pg_catalog."default",
    is_active boolean,
    is_kyc_verified boolean,
    aadhaar_no character varying(255) COLLATE pg_catalog."default",
    is_first_login boolean,
    email character varying(255) COLLATE pg_catalog."default",
    whatsapp_number character varying(255) COLLATE pg_catalog."default",
    user_type character varying(255) COLLATE pg_catalog."default" NOT NULL,
    reg_no bigint,
    created_at timestamp without time zone DEFAULT now(),
    is_document_verified boolean DEFAULT false,
    updated_at timestamp without time zone DEFAULT now(),
    dob character varying(64) COLLATE pg_catalog."default",
    gender character varying(64) COLLATE pg_catalog."default",
    country_type character varying(50) COLLATE pg_catalog."default",
    CONSTRAINT k_smart_user_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.k_smart_user
    OWNER to postgres;