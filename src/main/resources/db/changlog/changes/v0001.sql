CREATE TABLE public.publisher_user
(
    id uuid NOT NULL,
    email character varying(255) COLLATE pg_catalog."default",
    password character varying(255) COLLATE pg_catalog."default",
    scope boolean NOT NULL,
    username character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT publisher_user_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
);


ALTER TABLE public.publisher_user
    OWNER to postgres;



CREATE TABLE public.api_document
(
    id uuid NOT NULL,
    content jsonb,
    is_scope_private boolean NOT NULL,
    tag character varying(255) COLLATE pg_catalog."default",
    publisher_id uuid,
    CONSTRAINT api_document_pkey PRIMARY KEY (id),
    CONSTRAINT fk_apidoc_publisher FOREIGN KEY (publisher_id)
        REFERENCES public.publisher_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
);


ALTER TABLE public.api_document
    OWNER to postgres;


