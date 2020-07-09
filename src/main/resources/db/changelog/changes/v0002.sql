
ALTER TABLE public.publisher_user ADD COLUMN created_at TIMESTAMP;
ALTER TABLE public.publisher_user ALTER COLUMN created_at SET DEFAULT now();


ALTER TABLE public.api_document ADD COLUMN created_at TIMESTAMP;
ALTER TABLE public.api_document ALTER COLUMN created_at SET DEFAULT now();



