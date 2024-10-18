-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema nexsys_marketplace
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `nexsys_marketplace` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `nexsys_marketplace` DEFAULT CHARACTER SET utf8 ;
USE `nexsys_marketplace` ;

-- -----------------------------------------------------
-- Table `categoria_nexsys`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `categoria_nexsys` ;

CREATE TABLE IF NOT EXISTS `categoria_nexsys` (
  `c_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`c_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `producto_nexsys`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `producto_nexsys` ;

CREATE TABLE IF NOT EXISTS `producto_nexsys` (
  `p_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `buy_price` DECIMAL(10,2) NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `image` VARCHAR(500) NULL,
  `category_id` INT NOT NULL,
  PRIMARY KEY (`p_id`),
  INDEX `fk_producto_nexsys_categoria_nexsys_idx` (`category_id` ASC) VISIBLE,
  CONSTRAINT `fk_producto_nexsys_categoria_nexsys`
    FOREIGN KEY (`category_id`)
    REFERENCES `categoria_nexsys` (`c_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
