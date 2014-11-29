
CREATE SEQUENCE public.classificacao_biblioteca_classificacaoid_seq;

CREATE TABLE public.classificacao_biblioteca (
                classificacaoid INTEGER NOT NULL DEFAULT nextval('public.classificacao_biblioteca_classificacaoid_seq'),
                nome VARCHAR(30) NOT NULL,
                CONSTRAINT classificacao_biblioteca_pk PRIMARY KEY (classificacaoid)
);
COMMENT ON TABLE public.classificacao_biblioteca IS 'Tabela para armazenar a classifica��o das bibliotecas. Exemplo: �timo, Muito bom, bom, ruim e p�ssimo.';


ALTER SEQUENCE public.classificacao_biblioteca_classificacaoid_seq OWNED BY public.classificacao_biblioteca.classificacaoid;

CREATE SEQUENCE public.tipo_biblioteca_tipoid_seq;

CREATE TABLE public.tipo_biblioteca (
                tipoid INTEGER NOT NULL DEFAULT nextval('public.tipo_biblioteca_tipoid_seq'),
                nome VARCHAR(30) NOT NULL,
                CONSTRAINT pk_tipo_biblioteca PRIMARY KEY (tipoid)
);
COMMENT ON TABLE public.tipo_biblioteca IS 'Tabela para armazenar os tipos de biblioteca. Exemplo: Filmes, M�sicas, Seriados, Livros.';


ALTER SEQUENCE public.tipo_biblioteca_tipoid_seq OWNED BY public.tipo_biblioteca.tipoid;

CREATE SEQUENCE public.usuario_usuarioid_seq;

CREATE TABLE public.usuario (
                usuarioid INTEGER NOT NULL DEFAULT nextval('public.usuario_usuarioid_seq'),
                nome VARCHAR(100) NOT NULL,
                senha VARCHAR(32) NOT NULL,
                email VARCHAR(100) NOT NULL,
                CONSTRAINT pk_usuario PRIMARY KEY (usuarioid)
);
COMMENT ON TABLE public.usuario IS 'Tabela para armazenar os usu�rios cadastrados no sistema.';


ALTER SEQUENCE public.usuario_usuarioid_seq OWNED BY public.usuario.usuarioid;

CREATE SEQUENCE public.biblioteca_bibliotecaid_seq;

CREATE TABLE public.biblioteca (
                bibliotecaid INTEGER NOT NULL DEFAULT nextval('public.biblioteca_bibliotecaid_seq'),
                usuarioid INTEGER NOT NULL,
                classificacaoid INTEGER NOT NULL,
                tipoid INTEGER NOT NULL,
                nome VARCHAR(100) NOT NULL,
                desejado BOOLEAN NOT NULL,
                CONSTRAINT pk_biblioteca PRIMARY KEY (bibliotecaid)
);
COMMENT ON TABLE public.biblioteca IS 'Tabela que armazena o cadastro das bibliotecas por usu�rio.';


ALTER SEQUENCE public.biblioteca_bibliotecaid_seq OWNED BY public.biblioteca.bibliotecaid;

CREATE SEQUENCE public.emprestimo_emprestimoid_seq;

CREATE TABLE public.emprestimo (
                emprestimoid INTEGER NOT NULL DEFAULT nextval('public.emprestimo_emprestimoid_seq'),
                bibliotecaid INTEGER NOT NULL,
                data_emprestimo DATE NOT NULL,
                data_encerramento DATE NOT NULL,
                destinatario VARCHAR(100) NOT NULL,
                observacao VARCHAR(200) NOT NULL,
                ativo BOOLEAN NOT NULL,
                CONSTRAINT pk_emprestimo PRIMARY KEY (emprestimoid)
);
COMMENT ON TABLE public.emprestimo IS 'Tabela para armazenar os empr�stimos realizados das bibliotecas para outras pessoas.';


ALTER SEQUENCE public.emprestimo_emprestimoid_seq OWNED BY public.emprestimo.emprestimoid;

ALTER TABLE public.biblioteca ADD CONSTRAINT fk_classificacao_biblioteca_biblioteca
FOREIGN KEY (classificacaoid)
REFERENCES public.classificacao_biblioteca (classificacaoid)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.biblioteca ADD CONSTRAINT tipo_biblioteca_biblioteca_fk
FOREIGN KEY (tipoid)
REFERENCES public.tipo_biblioteca (tipoid)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.biblioteca ADD CONSTRAINT fk_usuario_biblioteca
FOREIGN KEY (usuarioid)
REFERENCES public.usuario (usuarioid)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.emprestimo ADD CONSTRAINT biblioteca_emprestimo_fk
FOREIGN KEY (bibliotecaid)
REFERENCES public.biblioteca (bibliotecaid)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
