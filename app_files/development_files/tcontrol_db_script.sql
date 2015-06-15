SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `tcontrol` ;
CREATE SCHEMA IF NOT EXISTS `tcontrol` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `tcontrol` ;

-- -----------------------------------------------------
-- Table `tcontrol`.`pessoas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tcontrol`.`pessoas` ;

CREATE TABLE IF NOT EXISTS `tcontrol`.`pessoas` (
  `id_pessoa` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_pessoa`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tcontrol`.`usuarios`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tcontrol`.`usuarios` ;

CREATE TABLE IF NOT EXISTS `tcontrol`.`usuarios` (
  `id_usuario` INT NOT NULL AUTO_INCREMENT,
  `nome_de_usuario` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `ativo` TINYINT(1) NOT NULL DEFAULT false,
  `exp_data` TINYINT(1) NULL,
  `admin` TINYINT(1) NOT NULL DEFAULT false,
  PRIMARY KEY (`id_usuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tcontrol`.`funcionarios`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tcontrol`.`funcionarios` ;

CREATE TABLE IF NOT EXISTS `tcontrol`.`funcionarios` (
  `id_funcionario` INT NOT NULL,
  `telefone` MEDIUMTEXT NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `data_saida` DATE NULL,
  `id_usuario` INT NOT NULL,
  PRIMARY KEY (`id_funcionario`),
  CONSTRAINT `fk_Funcionarios_Pessoas`
    FOREIGN KEY (`id_funcionario`)
    REFERENCES `tcontrol`.`pessoas` (`id_pessoa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Funcionarios_Usuarios1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `tcontrol`.`usuarios` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Funcionarios_Usuarios1_idx` ON `tcontrol`.`funcionarios` (`id_usuario` ASC);


-- -----------------------------------------------------
-- Table `tcontrol`.`grupos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tcontrol`.`grupos` ;

CREATE TABLE IF NOT EXISTS `tcontrol`.`grupos` (
  `id_grupo` INT NOT NULL AUTO_INCREMENT,
  `sigla` VARCHAR(10) NOT NULL,
  `nome` VARCHAR(100) NOT NULL,
  `ativo` TINYINT(1) NOT NULL DEFAULT false,
  PRIMARY KEY (`id_grupo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tcontrol`.`utilizadores`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tcontrol`.`utilizadores` ;

CREATE TABLE IF NOT EXISTS `tcontrol`.`utilizadores` (
  `id_utilizador` INT NOT NULL AUTO_INCREMENT,
  `dsc` VARCHAR(12) NOT NULL,
  `id_grupo` INT NOT NULL,
  PRIMARY KEY (`id_utilizador`),
  CONSTRAINT `fk_Alunos_Pessoas1`
    FOREIGN KEY (`id_utilizador`)
    REFERENCES `tcontrol`.`pessoas` (`id_pessoa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Alunos_cursos1`
    FOREIGN KEY (`id_grupo`)
    REFERENCES `tcontrol`.`grupos` (`id_grupo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Alunos_cursos1_idx` ON `tcontrol`.`utilizadores` (`id_grupo` ASC);


-- -----------------------------------------------------
-- Table `tcontrol`.`setores`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tcontrol`.`setores` ;

CREATE TABLE IF NOT EXISTS `tcontrol`.`setores` (
  `id_setor` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `sigla` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id_setor`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tcontrol`.`mapa_de_servicos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tcontrol`.`mapa_de_servicos` ;

CREATE TABLE IF NOT EXISTS `tcontrol`.`mapa_de_servicos` (
  `id_mapa` INT NOT NULL AUTO_INCREMENT,
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
    REFERENCES `tcontrol`.`setores` (`id_setor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_mapa_de_servicos_Funcionarios1`
    FOREIGN KEY (`idFuncionario`)
    REFERENCES `tcontrol`.`funcionarios` (`id_funcionario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_mapa_de_servicos_Setores1_idx` ON `tcontrol`.`mapa_de_servicos` (`idSetor` ASC);

CREATE INDEX `fk_mapa_de_servicos_Funcionarios1_idx` ON `tcontrol`.`mapa_de_servicos` (`idFuncionario` ASC);


-- -----------------------------------------------------
-- Table `tcontrol`.`excecoes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tcontrol`.`excecoes` ;

CREATE TABLE IF NOT EXISTS `tcontrol`.`excecoes` (
  `id_exececao` INT NOT NULL AUTO_INCREMENT,
  `mapa_de_servicos_id_mapa` INT NOT NULL,
  `data_excecao` DATE NOT NULL,
  PRIMARY KEY (`id_exececao`),
  CONSTRAINT `fk_excecoes_mapa_de_servicos1`
    FOREIGN KEY (`mapa_de_servicos_id_mapa`)
    REFERENCES `tcontrol`.`mapa_de_servicos` (`id_mapa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_excecoes_mapa_de_servicos1_idx` ON `tcontrol`.`excecoes` (`mapa_de_servicos_id_mapa` ASC);


-- -----------------------------------------------------
-- Table `tcontrol`.`terminais`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tcontrol`.`terminais` ;

CREATE TABLE IF NOT EXISTS `tcontrol`.`terminais` (
  `id_terminal` INT NOT NULL AUTO_INCREMENT,
  `hostname` VARCHAR(45) NOT NULL,
  `ip_address` VARCHAR(15) NOT NULL,
  `ativo` TINYINT(1) NOT NULL,
  `id_setor` INT NOT NULL,
  PRIMARY KEY (`id_terminal`),
  CONSTRAINT `fk_terminais_Setores1`
    FOREIGN KEY (`id_setor`)
    REFERENCES `tcontrol`.`setores` (`id_setor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_terminais_Setores1_idx` ON `tcontrol`.`terminais` (`id_setor` ASC);


-- -----------------------------------------------------
-- Table `tcontrol`.`bloqueios`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tcontrol`.`bloqueios` ;

CREATE TABLE IF NOT EXISTS `tcontrol`.`bloqueios` (
  `id_bloqueio` INT NOT NULL AUTO_INCREMENT,
  `data_inicio` DATETIME NOT NULL,
  `data_fim` DATETIME NULL,
  `dsc` VARCHAR(100) NULL,
  PRIMARY KEY (`id_bloqueio`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tcontrol`.`bloqueios_setores`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tcontrol`.`bloqueios_setores` ;

CREATE TABLE IF NOT EXISTS `tcontrol`.`bloqueios_setores` (
  `id_bloqueio` INT NOT NULL,
  `id_setor` INT NOT NULL,
  PRIMARY KEY (`id_bloqueio`, `id_setor`),
  CONSTRAINT `fk_bloqueios_setores_bloqueios1`
    FOREIGN KEY (`id_bloqueio`)
    REFERENCES `tcontrol`.`bloqueios` (`id_bloqueio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_bloqueios_setores_setores1`
    FOREIGN KEY (`id_setor`)
    REFERENCES `tcontrol`.`setores` (`id_setor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_bloqueios_setores_bloqueios1_idx` ON `tcontrol`.`bloqueios_setores` (`id_bloqueio` ASC);

CREATE INDEX `fk_bloqueios_setores_setores1_idx` ON `tcontrol`.`bloqueios_setores` (`id_setor` ASC);


-- -----------------------------------------------------
-- Table `tcontrol`.`bloqueios_terminais`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tcontrol`.`bloqueios_terminais` ;

CREATE TABLE IF NOT EXISTS `tcontrol`.`bloqueios_terminais` (
  `id_bloqueio` INT NOT NULL,
  `id_terminail` INT NOT NULL,
  PRIMARY KEY (`id_bloqueio`, `id_terminail`),
  CONSTRAINT `fk_bloqueios_terminais_bloqueios1`
    FOREIGN KEY (`id_bloqueio`)
    REFERENCES `tcontrol`.`bloqueios` (`id_bloqueio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_bloqueios_terminais_terminais1`
    FOREIGN KEY (`id_terminail`)
    REFERENCES `tcontrol`.`terminais` (`id_terminal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_bloqueios_terminais_bloqueios1_idx` ON `tcontrol`.`bloqueios_terminais` (`id_bloqueio` ASC);

CREATE INDEX `fk_bloqueios_terminais_terminais1_idx` ON `tcontrol`.`bloqueios_terminais` (`id_terminail` ASC);


-- -----------------------------------------------------
-- Table `tcontrol`.`bloqueios_grupo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tcontrol`.`bloqueios_grupo` ;

CREATE TABLE IF NOT EXISTS `tcontrol`.`bloqueios_grupo` (
  `id_bloqueio` INT NOT NULL,
  `id_grupo` INT NOT NULL,
  PRIMARY KEY (`id_bloqueio`, `id_grupo`),
  CONSTRAINT `fk_bloqueios_cursos_cursos1`
    FOREIGN KEY (`id_grupo`)
    REFERENCES `tcontrol`.`grupos` (`id_grupo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_bloqueios_cursos_bloqueios1`
    FOREIGN KEY (`id_bloqueio`)
    REFERENCES `tcontrol`.`bloqueios` (`id_bloqueio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_bloqueios_cursos_cursos1_idx` ON `tcontrol`.`bloqueios_grupo` (`id_grupo` ASC);

CREATE INDEX `fk_bloqueios_cursos_bloqueios1_idx` ON `tcontrol`.`bloqueios_grupo` (`id_bloqueio` ASC);


-- -----------------------------------------------------
-- Table `tcontrol`.`registros`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tcontrol`.`registros` ;

CREATE TABLE IF NOT EXISTS `tcontrol`.`registros` (
  `id_registros` INT NOT NULL AUTO_INCREMENT,
  `id_utilizador` INT NOT NULL,
  `id_terminal` INT NOT NULL,
  `id_funcionario` INT NOT NULL,
  `data_entrada` DATE NOT NULL,
  `data_saida` DATE NULL,
  PRIMARY KEY (`id_registros`),
  CONSTRAINT `fk_registros_Alunos1`
    FOREIGN KEY (`id_utilizador`)
    REFERENCES `tcontrol`.`utilizadores` (`id_utilizador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_registros_terminais1`
    FOREIGN KEY (`id_terminal`)
    REFERENCES `tcontrol`.`terminais` (`id_terminal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_registros_Funcionarios1`
    FOREIGN KEY (`id_funcionario`)
    REFERENCES `tcontrol`.`funcionarios` (`id_funcionario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_registros_Alunos1_idx` ON `tcontrol`.`registros` (`id_utilizador` ASC);

CREATE INDEX `fk_registros_terminais1_idx` ON `tcontrol`.`registros` (`id_terminal` ASC);

CREATE INDEX `fk_registros_Funcionarios1_idx` ON `tcontrol`.`registros` (`id_funcionario` ASC);


-- -----------------------------------------------------
-- Table `tcontrol`.`bloqueios_utilizadores`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tcontrol`.`bloqueios_utilizadores` ;

CREATE TABLE IF NOT EXISTS `tcontrol`.`bloqueios_utilizadores` (
  `id_bloqueio` INT NOT NULL,
  `id_utilizador` INT NOT NULL,
  PRIMARY KEY (`id_bloqueio`, `id_utilizador`),
  CONSTRAINT `fk_bloqueios_utilizadores_bloqueios1`
    FOREIGN KEY (`id_bloqueio`)
    REFERENCES `tcontrol`.`bloqueios` (`id_bloqueio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_bloqueios_utilizadores_utilizadores1`
    FOREIGN KEY (`id_utilizador`)
    REFERENCES `tcontrol`.`utilizadores` (`id_utilizador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_bloqueios_utilizadores_bloqueios1_idx` ON `tcontrol`.`bloqueios_utilizadores` (`id_bloqueio` ASC);

CREATE INDEX `fk_bloqueios_utilizadores_utilizadores1_idx` ON `tcontrol`.`bloqueios_utilizadores` (`id_utilizador` ASC);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
