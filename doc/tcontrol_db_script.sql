SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `mydb` ;
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`pessoas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`pessoas` ;

CREATE TABLE IF NOT EXISTS `mydb`.`pessoas` (
  `id_pessoa` INT NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_pessoa`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`usuarios`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`usuarios` ;

CREATE TABLE IF NOT EXISTS `mydb`.`usuarios` (
  `id_usuario` INT NOT NULL,
  `nome_de_usuario` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `ativo` TINYINT(1) NOT NULL DEFAULT false,
  `exp_data` TINYINT(1) NULL,
  `admin` TINYINT(1) NOT NULL DEFAULT false,
  PRIMARY KEY (`id_usuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`funcionarios`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`funcionarios` ;

CREATE TABLE IF NOT EXISTS `mydb`.`funcionarios` (
  `id_funcionario` INT NOT NULL,
  `telefone` MEDIUMTEXT NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `data_saida` DATE NULL,
  `id_usuario` INT NOT NULL,
  PRIMARY KEY (`id_funcionario`),
  CONSTRAINT `fk_Funcionarios_Pessoas`
    FOREIGN KEY (`id_funcionario`)
    REFERENCES `mydb`.`pessoas` (`id_pessoa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Funcionarios_Usuarios1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `mydb`.`usuarios` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Funcionarios_Usuarios1_idx` ON `mydb`.`funcionarios` (`id_usuario` ASC);


-- -----------------------------------------------------
-- Table `mydb`.`grupos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`grupos` ;

CREATE TABLE IF NOT EXISTS `mydb`.`grupos` (
  `id_grupo` INT NOT NULL,
  `sigla` VARCHAR(10) NOT NULL,
  `nome` VARCHAR(100) NOT NULL,
  `ativo` TINYINT(1) NOT NULL DEFAULT false,
  PRIMARY KEY (`id_grupo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`utilizadores`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`utilizadores` ;

CREATE TABLE IF NOT EXISTS `mydb`.`utilizadores` (
  `id_utilizador` INT NOT NULL,
  `dsc` VARCHAR(12) NOT NULL,
  `id_grupo` INT NOT NULL,
  PRIMARY KEY (`id_utilizador`),
  CONSTRAINT `fk_Alunos_Pessoas1`
    FOREIGN KEY (`id_utilizador`)
    REFERENCES `mydb`.`pessoas` (`id_pessoa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Alunos_cursos1`
    FOREIGN KEY (`id_grupo`)
    REFERENCES `mydb`.`grupos` (`id_grupo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Alunos_cursos1_idx` ON `mydb`.`utilizadores` (`id_grupo` ASC);


-- -----------------------------------------------------
-- Table `mydb`.`setores`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`setores` ;

CREATE TABLE IF NOT EXISTS `mydb`.`setores` (
  `id_setor` INT NOT NULL,
  `nome` VARCHAR(100) NOT NULL,
  `sigla` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id_setor`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`mapa_de_servicos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`mapa_de_servicos` ;

CREATE TABLE IF NOT EXISTS `mydb`.`mapa_de_servicos` (
  `id_mapa` INT NOT NULL,
  `idFuncionario` INT NOT NULL,
  `idSetor` INT NOT NULL,
  `horario_entrada` TIME NOT NULL,
  `horario_saida` TIME NOT NULL,
  `seg` TINYINT(1) NOT NULL DEFAULT false,
  `ter` TINYINT(1) NOT NULL DEFAULT false,
  `qua` TINYINT(1) NOT NULL DEFAULT false,
  `qui` TINYINT(1) NOT NULL DEFAULT false,
  `sex` TINYINT(1) NOT NULL DEFAULT false,
  `sab` TINYINT(1) NOT NULL DEFAULT false,
  `dom` TINYINT(1) NOT NULL DEFAULT false,
  PRIMARY KEY (`id_mapa`),
  CONSTRAINT `fk_mapa_de_servicos_Setores1`
    FOREIGN KEY (`idSetor`)
    REFERENCES `mydb`.`setores` (`id_setor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_mapa_de_servicos_Funcionarios1`
    FOREIGN KEY (`idFuncionario`)
    REFERENCES `mydb`.`funcionarios` (`id_funcionario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_mapa_de_servicos_Setores1_idx` ON `mydb`.`mapa_de_servicos` (`idSetor` ASC);

CREATE INDEX `fk_mapa_de_servicos_Funcionarios1_idx` ON `mydb`.`mapa_de_servicos` (`idFuncionario` ASC);


-- -----------------------------------------------------
-- Table `mydb`.`excecoes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`excecoes` ;

CREATE TABLE IF NOT EXISTS `mydb`.`excecoes` (
  `id_exececao` INT NOT NULL,
  `mapa_de_servicos_id_mapa` INT NOT NULL,
  `data_excecao` DATE NOT NULL,
  PRIMARY KEY (`id_exececao`),
  CONSTRAINT `fk_excecoes_mapa_de_servicos1`
    FOREIGN KEY (`mapa_de_servicos_id_mapa`)
    REFERENCES `mydb`.`mapa_de_servicos` (`id_mapa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_excecoes_mapa_de_servicos1_idx` ON `mydb`.`excecoes` (`mapa_de_servicos_id_mapa` ASC);


-- -----------------------------------------------------
-- Table `mydb`.`terminais`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`terminais` ;

CREATE TABLE IF NOT EXISTS `mydb`.`terminais` (
  `id_terminal` INT NOT NULL,
  `hostname` VARCHAR(45) NOT NULL,
  `ip_address` VARCHAR(15) NOT NULL,
  `ativo` TINYINT(1) NOT NULL,
  `id_setor` INT NOT NULL,
  PRIMARY KEY (`id_terminal`),
  CONSTRAINT `fk_terminais_Setores1`
    FOREIGN KEY (`id_setor`)
    REFERENCES `mydb`.`setores` (`id_setor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_terminais_Setores1_idx` ON `mydb`.`terminais` (`id_setor` ASC);


-- -----------------------------------------------------
-- Table `mydb`.`bloqueios`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`bloqueios` ;

CREATE TABLE IF NOT EXISTS `mydb`.`bloqueios` (
  `id_bloqueio` INT NOT NULL,
  `data_inicio` DATETIME NOT NULL,
  `data_fim` DATETIME NULL,
  `dsc` VARCHAR(100) NULL,
  `id_utilizador` INT NOT NULL,
  PRIMARY KEY (`id_bloqueio`),
  CONSTRAINT `fk_bloqueio_aluno_Alunos1`
    FOREIGN KEY (`id_utilizador`)
    REFERENCES `mydb`.`utilizadores` (`id_utilizador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_bloqueio_aluno_Alunos1_idx` ON `mydb`.`bloqueios` (`id_utilizador` ASC);


-- -----------------------------------------------------
-- Table `mydb`.`bloqueios_setores`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`bloqueios_setores` ;

CREATE TABLE IF NOT EXISTS `mydb`.`bloqueios_setores` (
  `id_bloqueio` INT NOT NULL,
  `id_setor` INT NOT NULL,
  CONSTRAINT `fk_bloqueios_setores_bloqueios1`
    FOREIGN KEY (`id_bloqueio`)
    REFERENCES `mydb`.`bloqueios` (`id_bloqueio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_bloqueios_setores_setores1`
    FOREIGN KEY (`id_setor`)
    REFERENCES `mydb`.`setores` (`id_setor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_bloqueios_setores_bloqueios1_idx` ON `mydb`.`bloqueios_setores` (`id_bloqueio` ASC);

CREATE INDEX `fk_bloqueios_setores_setores1_idx` ON `mydb`.`bloqueios_setores` (`id_setor` ASC);


-- -----------------------------------------------------
-- Table `mydb`.`bloqueios_terminais`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`bloqueios_terminais` ;

CREATE TABLE IF NOT EXISTS `mydb`.`bloqueios_terminais` (
  `id_bloqueio` INT NOT NULL,
  `id_terminail` INT NOT NULL,
  CONSTRAINT `fk_bloqueios_terminais_bloqueios1`
    FOREIGN KEY (`id_bloqueio`)
    REFERENCES `mydb`.`bloqueios` (`id_bloqueio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_bloqueios_terminais_terminais1`
    FOREIGN KEY (`id_terminail`)
    REFERENCES `mydb`.`terminais` (`id_terminal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_bloqueios_terminais_bloqueios1_idx` ON `mydb`.`bloqueios_terminais` (`id_bloqueio` ASC);

CREATE INDEX `fk_bloqueios_terminais_terminais1_idx` ON `mydb`.`bloqueios_terminais` (`id_terminail` ASC);


-- -----------------------------------------------------
-- Table `mydb`.`bloqueios_grupo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`bloqueios_grupo` ;

CREATE TABLE IF NOT EXISTS `mydb`.`bloqueios_grupo` (
  `id_bloqueio` INT NOT NULL,
  `id_curso` INT NOT NULL,
  CONSTRAINT `fk_bloqueios_cursos_cursos1`
    FOREIGN KEY (`id_curso`)
    REFERENCES `mydb`.`grupos` (`id_grupo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_bloqueios_cursos_bloqueios1`
    FOREIGN KEY (`id_bloqueio`)
    REFERENCES `mydb`.`bloqueios` (`id_bloqueio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_bloqueios_cursos_cursos1_idx` ON `mydb`.`bloqueios_grupo` (`id_curso` ASC);

CREATE INDEX `fk_bloqueios_cursos_bloqueios1_idx` ON `mydb`.`bloqueios_grupo` (`id_bloqueio` ASC);


-- -----------------------------------------------------
-- Table `mydb`.`registros`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`registros` ;

CREATE TABLE IF NOT EXISTS `mydb`.`registros` (
  `id_registros` INT NOT NULL,
  `id_utilizador` INT NOT NULL,
  `id_terminal` INT NOT NULL,
  `id_funcionario` INT NOT NULL,
  `data_entrada` DATE NOT NULL,
  `data_saida` DATE NULL,
  PRIMARY KEY (`id_registros`),
  CONSTRAINT `fk_registros_Alunos1`
    FOREIGN KEY (`id_utilizador`)
    REFERENCES `mydb`.`utilizadores` (`id_utilizador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_registros_terminais1`
    FOREIGN KEY (`id_terminal`)
    REFERENCES `mydb`.`terminais` (`id_terminal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_registros_Funcionarios1`
    FOREIGN KEY (`id_funcionario`)
    REFERENCES `mydb`.`funcionarios` (`id_funcionario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_registros_Alunos1_idx` ON `mydb`.`registros` (`id_utilizador` ASC);

CREATE INDEX `fk_registros_terminais1_idx` ON `mydb`.`registros` (`id_terminal` ASC);

CREATE INDEX `fk_registros_Funcionarios1_idx` ON `mydb`.`registros` (`id_funcionario` ASC);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
