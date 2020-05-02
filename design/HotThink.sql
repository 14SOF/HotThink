SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema HotThink
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema HotThink
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `HotThink` DEFAULT CHARACTER SET utf8 ;
USE `HotThink` ;

-- -----------------------------------------------------
-- Table `HotThink`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HotThink`.`user` (
  `user_idx` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(100) NOT NULL,
  `user_pw` VARCHAR(32) NOT NULL,
  `user_nick` VARCHAR(20) NOT NULL,
  `user_name` VARCHAR(20) NULL,
  `user_phone` VARCHAR(20) NULL,
  `user_status` TINYINT(1) NOT NULL,
  PRIMARY KEY (`user_idx`),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC),
  UNIQUE INDEX `user_nick_UNIQUE` (`user_nick` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HotThink`.`post`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HotThink`.`post` (
  `post_idx` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `post_title` VARCHAR(50) NOT NULL,
  `post_content` MEDIUMTEXT NOT NULL,
  `post_hit` INT UNSIGNED NOT NULL,
  `post_like` INT UNSIGNED NOT NULL,
  `post_dateTime` DATETIME NOT NULL,
  `post_type` ENUM('프리', '핫', '리얼', '공지') NOT NULL,
  `user_user_idx` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`post_idx`, `user_user_idx`),
  INDEX `fk_post_user1_idx` (`user_user_idx` ASC),
  CONSTRAINT `fk_post_user1`
    FOREIGN KEY (`user_user_idx`)
    REFERENCES `HotThink`.`user` (`user_idx`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HotThink`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HotThink`.`comment` (
  `comment_idx` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `comment_content` TEXT NOT NULL,
  `comment_hit` INT UNSIGNED NOT NULL,
  `comment_dateTime` DATETIME NOT NULL,
  `post_post_idx` INT UNSIGNED NOT NULL,
  `user_user_idx` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`comment_idx`, `post_post_idx`, `user_user_idx`),
  INDEX `fk_comment_post1_idx` (`post_post_idx` ASC),
  INDEX `fk_comment_user1_idx` (`user_user_idx` ASC),
  CONSTRAINT `fk_comment_post1`
    FOREIGN KEY (`post_post_idx`)
    REFERENCES `HotThink`.`post` (`post_idx`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_user1`
    FOREIGN KEY (`user_user_idx`)
    REFERENCES `HotThink`.`user` (`user_idx`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HotThink`.`message`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HotThink`.`message` (
  `message_idx` INT NOT NULL AUTO_INCREMENT,
  `message_content` VARCHAR(255) NOT NULL,
  `message_dateTime` DATETIME NOT NULL,
  `message_read` TINYINT NOT NULL,
  `user_receiver` INT UNSIGNED NOT NULL,
  `user_sender` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`message_idx`, `user_receiver`, `user_sender`),
  INDEX `fk_message_user1_idx` (`user_receiver` ASC),
  INDEX `fk_message_user2_idx` (`user_sender` ASC),
  CONSTRAINT `fk_message_receiver`
    FOREIGN KEY (`user_receiver`)
    REFERENCES `HotThink`.`user` (`user_idx`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_message_sender`
    FOREIGN KEY (`user_sender`)
    REFERENCES `HotThink`.`user` (`user_idx`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HotThink`.`report`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HotThink`.`report` (
  `report_idx` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `report_dateTime` DATETIME NOT NULL,
  `report_type` ENUM('1', '2', '3') NOT NULL,
  `report_typeKey` INT UNSIGNED NOT NULL,
  `user_user_idx` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`report_idx`, `user_user_idx`),
  INDEX `fk_report_user1_idx` (`user_user_idx` ASC),
  CONSTRAINT `fk_report_user1`
    FOREIGN KEY (`user_user_idx`)
    REFERENCES `HotThink`.`user` (`user_idx`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HotThink`.`follow`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HotThink`.`follow` (
  `follow_idx` INT NOT NULL AUTO_INCREMENT,
  `user_follower` INT UNSIGNED NOT NULL,
  `user_following` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`follow_idx`, `user_follower`, `user_following`),
  INDEX `fk_follow_user1_idx` (`user_follower` ASC),
  INDEX `fk_follow_user2_idx` (`user_following` ASC),
  CONSTRAINT `fk_follow_user1`
    FOREIGN KEY (`user_follower`)
    REFERENCES `HotThink`.`user` (`user_idx`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_follow_user2`
    FOREIGN KEY (`user_following`)
    REFERENCES `HotThink`.`user` (`user_idx`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HotThink`.`point`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HotThink`.`point` (
  `point_idx` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `point_amount` INT NOT NULL,
  `point_dateTime` DATETIME NOT NULL,
  `user_user_idx` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`point_idx`, `user_user_idx`),
  INDEX `fk_point_user1_idx` (`user_user_idx` ASC),
  CONSTRAINT `fk_point_user1`
    FOREIGN KEY (`user_user_idx`)
    REFERENCES `HotThink`.`user` (`user_idx`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HotThink`.`rate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HotThink`.`rate` (
  `rate_idx` INT NOT NULL,
  `rate_score` FLOAT NOT NULL,
  `rate_content` TEXT NULL,
  `rate_dateTime` DATETIME NULL,
  `user_user_idx` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`rate_idx`, `user_user_idx`),
  INDEX `fk_rate_user1_idx` (`user_user_idx` ASC),
  CONSTRAINT `fk_rate_user1`
    FOREIGN KEY (`user_user_idx`)
    REFERENCES `HotThink`.`user` (`user_idx`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HotThink`.`transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HotThink`.`transaction` (
  `transaction_idx` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `transaction_dateTime` DATETIME NOT NULL,
  `transaction_price` INT UNSIGNED NOT NULL,
  `post_post_idx` INT UNSIGNED NOT NULL,
  `user_user_idx` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`transaction_idx`, `post_post_idx`, `user_user_idx`),
  INDEX `fk_transaction_post1_idx` (`post_post_idx` ASC),
  INDEX `fk_transaction_user1_idx` (`user_user_idx` ASC),
  CONSTRAINT `fk_transaction_post1`
    FOREIGN KEY (`post_post_idx`)
    REFERENCES `HotThink`.`post` (`post_idx`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_transaction_user1`
    FOREIGN KEY (`user_user_idx`)
    REFERENCES `HotThink`.`user` (`user_idx`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
