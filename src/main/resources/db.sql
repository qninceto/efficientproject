-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema efficientproject
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema efficientproject
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `efficientproject` DEFAULT CHARACTER SET utf8 ;
USE `efficientproject` ;

-- -----------------------------------------------------
-- Table `efficientproject`.`organizations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `efficientproject`.`organizations` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `efficientproject`.`projects`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `efficientproject`.`projects` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `deadline` DATE NOT NULL,
  `organization_id` INT(11) NOT NULL,
  `start_date` DATE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_projects_organizations1_idx` (`organization_id` ASC),
  CONSTRAINT `fk_projects_organizations1`
    FOREIGN KEY (`organization_id`)
    REFERENCES `efficientproject`.`organizations` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `efficientproject`.`epics`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `efficientproject`.`epics` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `estimate` INT(11) NOT NULL,
  `description` TEXT NOT NULL,
  `project_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_epics_projects1_idx` (`project_id` ASC),
  CONSTRAINT `fk_epics_projects1`
    FOREIGN KEY (`project_id`)
    REFERENCES `efficientproject`.`projects` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `efficientproject`.`sprints`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `efficientproject`.`sprints` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `start_date` DATE NOT NULL,
  `duration` INT(11) NOT NULL,
  `project_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `project_id_idx` (`project_id` ASC),
  CONSTRAINT `fk_sprints_projects1`
    FOREIGN KEY (`project_id`)
    REFERENCES `efficientproject`.`projects` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `efficientproject`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `efficientproject`.`users` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `avatar_path` VARCHAR(200) NULL DEFAULT NULL,
  `admin` TINYINT(4) NOT NULL,
  `organization_id` INT(11) NULL DEFAULT NULL,
  `is_employed` TINYINT(4) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  INDEX `fk_users_companies_idx` (`organization_id` ASC),
  CONSTRAINT `fk_users_companies`
    FOREIGN KEY (`organization_id`)
    REFERENCES `efficientproject`.`organizations` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 39
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `efficientproject`.`types`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `efficientproject`.`types` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `efficientproject`.`tasks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `efficientproject`.`tasks` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `type_id` INT(11) NOT NULL,
  `summary` VARCHAR(200) NOT NULL,
  `description` TEXT NOT NULL,
  `estimate` DOUBLE NOT NULL,
  `creation_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `added_to_sprint_date` TIMESTAMP NULL DEFAULT NULL,
  `assigned_date` TIMESTAMP NULL DEFAULT NULL,
  `finished_date` TIMESTAMP NULL DEFAULT NULL,
  `stopped_date` TIMESTAMP NULL DEFAULT NULL,
  `sprint_id` INT(11) NULL DEFAULT NULL,
  `reporter` INT(11) NOT NULL,
  `assignee` INT(11) NULL DEFAULT NULL,
  `epic_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_issues_sprints1_idx` (`sprint_id` ASC),
  INDEX `fk_issues_users1_idx` (`assignee` ASC),
  INDEX `fk_issues_users2_idx` (`reporter` ASC),
  INDEX `fk_tasks_epics1_idx` (`epic_id` ASC),
  INDEX `fk_tasks_types1_idx` (`type_id` ASC),
  CONSTRAINT `fk_issues_sprints1`
    FOREIGN KEY (`sprint_id`)
    REFERENCES `efficientproject`.`sprints` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_issues_users1`
    FOREIGN KEY (`assignee`)
    REFERENCES `efficientproject`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_issues_users2`
    FOREIGN KEY (`reporter`)
    REFERENCES `efficientproject`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tasks_epics1`
    FOREIGN KEY (`epic_id`)
    REFERENCES `efficientproject`.`epics` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tasks_types1`
    FOREIGN KEY (`type_id`)
    REFERENCES `efficientproject`.`types` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 15
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `efficientproject`.`users_projects_history`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `efficientproject`.`users_projects_history` (
  `users_id` INT(11) NOT NULL,
  `project_id` INT(11) NOT NULL,
  PRIMARY KEY (`users_id`, `project_id`),
  INDEX `fk_users_has_project_project1_idx` (`project_id` ASC),
  INDEX `fk_users_has_project_users1_idx` (`users_id` ASC),
  CONSTRAINT `fk_users_has_project_project1`
    FOREIGN KEY (`project_id`)
    REFERENCES `efficientproject`.`projects` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_has_project_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `efficientproject`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
