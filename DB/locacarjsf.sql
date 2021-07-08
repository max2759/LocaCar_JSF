-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jan 21, 2021 at 05:09 PM
-- Server version: 5.7.24
-- PHP Version: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `locacarjsf`
--

-- --------------------------------------------------------

--
-- Table structure for table `addresses`
--

CREATE TABLE `addresses`
(
    `ID`        int(11)                       NOT NULL,
    `ID_Cities` int(11) UNSIGNED              NOT NULL,
    `ID_Users`  int(11) UNSIGNED              NOT NULL,
    `Street`    varchar(255) COLLATE utf8_bin NOT NULL,
    `Number`    varchar(255) COLLATE utf8_bin NOT NULL,
    `Box`       varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `ads`
--

CREATE TABLE `ads`
(
    `ID`         int(10) UNSIGNED                         NOT NULL,
    `ID_Cars`    int(10) UNSIGNED                         NOT NULL,
    `Price`      double UNSIGNED                          NOT NULL,
    `Date_Start` date                                     NOT NULL,
    `Date_End`   date                                     NOT NULL,
    `Type_Ads`   enum ('Sale','Leasing') COLLATE utf8_bin NOT NULL,
    `Label`      varchar(255) COLLATE utf8_bin            NOT NULL,
    `IsActive`   tinyint(1)                               NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `brands`
--

CREATE TABLE `brands`
(
    `ID`    int(10) UNSIGNED              NOT NULL,
    `Label` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `cars`
--

CREATE TABLE `cars`
(
    `ID`               int(10) UNSIGNED NOT NULL,
    `ID_Models`        int(10) UNSIGNED NOT NULL,
    `ID_Cars_Types`    int(10) UNSIGNED NOT NULL,
    `ID_Cars_GearBox`  int(10) UNSIGNED NOT NULL,
    `ID_Cars_Fuels`    int(10) UNSIGNED NOT NULL,
    `ID_Cars_Colors`   int(10) UNSIGNED NOT NULL,
    `ID_Cars_Pictures` int(10) UNSIGNED NOT NULL,
    `Release_Year`     int(10) UNSIGNED NOT NULL,
    `HorsePower`       int(10) UNSIGNED NOT NULL,
    `Kilometer`        int(10) UNSIGNED NOT NULL,
    `IsActive`         tinyint(1)       NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `cars_colors`
--

CREATE TABLE `cars_colors`
(
    `ID`    int(10) UNSIGNED              NOT NULL,
    `Label` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `cars_fuels`
--

CREATE TABLE `cars_fuels`
(
    `ID`    int(10) UNSIGNED              NOT NULL,
    `Label` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `cars_gearbox`
--

CREATE TABLE `cars_gearbox`
(
    `ID`    int(10) UNSIGNED              NOT NULL,
    `Label` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `cars_options`
--

CREATE TABLE `cars_options`
(
    `ID`         int(10) UNSIGNED NOT NULL,
    `ID_Options` int(11) UNSIGNED NOT NULL,
    `ID_Cars`    int(11) UNSIGNED NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `cars_pictures`
--

CREATE TABLE `cars_pictures`
(
    `ID`    int(10) UNSIGNED              NOT NULL,
    `Label` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `cars_types`
--

CREATE TABLE `cars_types`
(
    `ID`            int(10) UNSIGNED              NOT NULL,
    `Label`         varchar(255) COLLATE utf8_bin NOT NULL,
    `Seats_Numbers` int(11)                       NOT NULL,
    `Doors_Numbers` int(11)                       NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `cities`
--

CREATE TABLE `cities`
(
    `ID`           int(10) UNSIGNED              NOT NULL,
    `ID_Countries` int(10) UNSIGNED              NOT NULL,
    `Region`       varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `Postal_Code`  int(10) UNSIGNED              NOT NULL,
    `Label`        varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `contracts`
--

CREATE TABLE `contracts`
(
    `ID`                 int(10) UNSIGNED NOT NULL,
    `ID_Orders`          int(10) UNSIGNED NOT NULL,
    `ID_Cars`            int(10) UNSIGNED NOT NULL,
    `ID_Contract_Type`   int(10) UNSIGNED NOT NULL,
    `Date_Start`         date             NOT NULL,
    `Date_End`           date                      DEFAULT NULL,
    `Final_Price`        double UNSIGNED  NOT NULL,
    `Choice_end_Leasing` tinyint(1)       NOT NULL DEFAULT '1'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `contract_insurances`
--

CREATE TABLE `contract_insurances`
(
    `ID`           int(10) UNSIGNED NOT NULL,
    `ID_Contract`  int(10) UNSIGNED NOT NULL,
    `ID_Insurance` int(10) UNSIGNED NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `contract_types`
--

CREATE TABLE `contract_types`
(
    `ID`    int(10) UNSIGNED              NOT NULL,
    `Label` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `countries`
--

CREATE TABLE `countries`
(
    `ID`    int(10) UNSIGNED              NOT NULL,
    `Label` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `insurances`
--

CREATE TABLE `insurances`
(
    `ID`          int(11) UNSIGNED              NOT NULL,
    `Label`       varchar(255) COLLATE utf8_bin NOT NULL,
    `Description` varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `Price`       double UNSIGNED               NOT NULL,
    `IsActive`    tinyint(1)                    NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;

--
-- Dumping data for table `insurances`
--

INSERT INTO `insurances` (`ID`, `Label`, `Description`, `Price`, `IsActive`)
VALUES (1, 'Test', 'Test', 10, 1);

-- --------------------------------------------------------

--
-- Table structure for table `models`
--

CREATE TABLE `models`
(
    `ID`        int(10) UNSIGNED              NOT NULL,
    `ID_Brands` int(10) UNSIGNED              NOT NULL,
    `Label`     varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `options`
--

CREATE TABLE `options`
(
    `ID`    int(10) UNSIGNED              NOT NULL,
    `Label` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders`
(
    `ID`           int(10) UNSIGNED                                        NOT NULL,
    `ID_Users`     int(10) UNSIGNED                                        NOT NULL,
    `Order_Date`   date                                                    NOT NULL,
    `Order_Statut` enum ('Validate','Canceled','Pending') COLLATE utf8_bin NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `permissions`
--

CREATE TABLE `permissions`
(
    `ID`    int(10) UNSIGNED              NOT NULL,
    `Label` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles`
(
    `ID`       int(10) UNSIGNED              NOT NULL,
    `Label`    varchar(255) COLLATE utf8_bin NOT NULL,
    `IsActive` tinyint(1)                    NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `roles_permissions`
--

CREATE TABLE `roles_permissions`
(
    `ID`             int(10) UNSIGNED NOT NULL,
    `ID_Roles`       int(10) UNSIGNED NOT NULL,
    `ID_Permissions` int(10) UNSIGNED NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users`
(
    `ID`            int(10) UNSIGNED              NOT NULL,
    `ID_Roles`      int(10) UNSIGNED              NOT NULL,
    `Firstname`     varchar(255) COLLATE utf8_bin NOT NULL,
    `Lastname`      varchar(255) COLLATE utf8_bin NOT NULL,
    `Username`      varchar(255) COLLATE utf8_bin NOT NULL,
    `Password`      varchar(255) COLLATE utf8_bin NOT NULL,
    `Register_Date` date                          NOT NULL,
    `Birthdate`     date                          NOT NULL,
    `VATNumber`     varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `IsActive`      tinyint(1)                    NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `users_ads`
--

CREATE TABLE `users_ads`
(
    `ID`       int(10) UNSIGNED NOT NULL,
    `ID_Users` int(10) UNSIGNED NOT NULL,
    `ID_Ads`   int(10) UNSIGNED NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `addresses`
--
ALTER TABLE `addresses`
    ADD PRIMARY KEY (`ID`),
    ADD KEY `ID_Cities` (`ID_Cities`),
    ADD KEY `ID_Users` (`ID_Users`);

--
-- Indexes for table `ads`
--
ALTER TABLE `ads`
    ADD PRIMARY KEY (`ID`),
    ADD KEY `ID_Cars` (`ID_Cars`);

--
-- Indexes for table `brands`
--
ALTER TABLE `brands`
    ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `cars`
--
ALTER TABLE `cars`
    ADD PRIMARY KEY (`ID`),
    ADD KEY `ID_Cars_Colors` (`ID_Cars_Colors`),
    ADD KEY `ID_Cars_Fuels` (`ID_Cars_Fuels`),
    ADD KEY `ID_Cars_GearBox` (`ID_Cars_GearBox`),
    ADD KEY `ID_Cars_Pictures` (`ID_Cars_Pictures`),
    ADD KEY `ID_Cars_Types` (`ID_Cars_Types`),
    ADD KEY `ID_Models` (`ID_Models`);

--
-- Indexes for table `cars_colors`
--
ALTER TABLE `cars_colors`
    ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `cars_fuels`
--
ALTER TABLE `cars_fuels`
    ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `cars_gearbox`
--
ALTER TABLE `cars_gearbox`
    ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `cars_options`
--
ALTER TABLE `cars_options`
    ADD PRIMARY KEY (`ID`),
    ADD KEY `ID_Cars` (`ID_Cars`),
    ADD KEY `ID_Options` (`ID_Options`);

--
-- Indexes for table `cars_pictures`
--
ALTER TABLE `cars_pictures`
    ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `cars_types`
--
ALTER TABLE `cars_types`
    ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `cities`
--
ALTER TABLE `cities`
    ADD PRIMARY KEY (`ID`),
    ADD KEY `ID_Countries` (`ID_Countries`);

--
-- Indexes for table `contracts`
--
ALTER TABLE `contracts`
    ADD PRIMARY KEY (`ID`),
    ADD KEY `ID_Cars` (`ID_Cars`),
    ADD KEY `ID_Contract_Type` (`ID_Contract_Type`),
    ADD KEY `ID_Orders` (`ID_Orders`);

--
-- Indexes for table `contract_insurances`
--
ALTER TABLE `contract_insurances`
    ADD PRIMARY KEY (`ID`),
    ADD KEY `ID_Contract` (`ID_Contract`),
    ADD KEY `ID_Insurance` (`ID_Insurance`);

--
-- Indexes for table `contract_types`
--
ALTER TABLE `contract_types`
    ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `countries`
--
ALTER TABLE `countries`
    ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `insurances`
--
ALTER TABLE `insurances`
    ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `models`
--
ALTER TABLE `models`
    ADD PRIMARY KEY (`ID`),
    ADD KEY `ID_Brands` (`ID_Brands`);

--
-- Indexes for table `options`
--
ALTER TABLE `options`
    ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
    ADD PRIMARY KEY (`ID`),
    ADD KEY `ID_Users` (`ID_Users`);

--
-- Indexes for table `permissions`
--
ALTER TABLE `permissions`
    ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
    ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `roles_permissions`
--
ALTER TABLE `roles_permissions`
    ADD PRIMARY KEY (`ID`),
    ADD KEY `ID_Permissions` (`ID_Permissions`),
    ADD KEY `ID_Roles` (`ID_Roles`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
    ADD PRIMARY KEY (`ID`),
    ADD KEY `RolesID` (`ID_Roles`);

--
-- Indexes for table `users_ads`
--
ALTER TABLE `users_ads`
    ADD PRIMARY KEY (`ID`),
    ADD KEY `ID_Ads` (`ID_Ads`),
    ADD KEY `ID_Users` (`ID_Users`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `addresses`
--
ALTER TABLE `addresses`
    MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ads`
--
ALTER TABLE `ads`
    MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `brands`
--
ALTER TABLE `brands`
    MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `cars`
--
ALTER TABLE `cars`
    MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `cars_colors`
--
ALTER TABLE `cars_colors`
    MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `cars_fuels`
--
ALTER TABLE `cars_fuels`
    MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `cars_gearbox`
--
ALTER TABLE `cars_gearbox`
    MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `cars_options`
--
ALTER TABLE `cars_options`
    MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `cars_pictures`
--
ALTER TABLE `cars_pictures`
    MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `cars_types`
--
ALTER TABLE `cars_types`
    MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `cities`
--
ALTER TABLE `cities`
    MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `contracts`
--
ALTER TABLE `contracts`
    MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `contract_insurances`
--
ALTER TABLE `contract_insurances`
    MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `contract_types`
--
ALTER TABLE `contract_types`
    MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `countries`
--
ALTER TABLE `countries`
    MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `insurances`
--
ALTER TABLE `insurances`
    MODIFY `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    AUTO_INCREMENT = 2;

--
-- AUTO_INCREMENT for table `models`
--
ALTER TABLE `models`
    MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `options`
--
ALTER TABLE `options`
    MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
    MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `permissions`
--
ALTER TABLE `permissions`
    MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
    MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `roles_permissions`
--
ALTER TABLE `roles_permissions`
    MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
    MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users_ads`
--
ALTER TABLE `users_ads`
    MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `addresses`
--
ALTER TABLE `addresses`
    ADD CONSTRAINT `addresses_ibfk_1` FOREIGN KEY (`ID_Cities`) REFERENCES `cities` (`ID`),
    ADD CONSTRAINT `addresses_ibfk_2` FOREIGN KEY (`ID_Users`) REFERENCES `users` (`ID`);

--
-- Constraints for table `ads`
--
ALTER TABLE `ads`
    ADD CONSTRAINT `ads_ibfk_1` FOREIGN KEY (`ID_Cars`) REFERENCES `cars` (`ID`);

--
-- Constraints for table `cars`
--
ALTER TABLE `cars`
    ADD CONSTRAINT `cars_ibfk_1` FOREIGN KEY (`ID_Cars_Colors`) REFERENCES `cars_colors` (`ID`),
    ADD CONSTRAINT `cars_ibfk_2` FOREIGN KEY (`ID_Cars_Fuels`) REFERENCES `cars_fuels` (`ID`),
    ADD CONSTRAINT `cars_ibfk_3` FOREIGN KEY (`ID_Cars_GearBox`) REFERENCES `cars_gearbox` (`ID`),
    ADD CONSTRAINT `cars_ibfk_4` FOREIGN KEY (`ID_Cars_Pictures`) REFERENCES `cars_pictures` (`ID`),
    ADD CONSTRAINT `cars_ibfk_5` FOREIGN KEY (`ID_Cars_Types`) REFERENCES `cars_types` (`ID`),
    ADD CONSTRAINT `cars_ibfk_6` FOREIGN KEY (`ID_Models`) REFERENCES `models` (`ID`);

--
-- Constraints for table `cars_options`
--
ALTER TABLE `cars_options`
    ADD CONSTRAINT `cars_options_ibfk_1` FOREIGN KEY (`ID_Cars`) REFERENCES `cars` (`ID`),
    ADD CONSTRAINT `cars_options_ibfk_2` FOREIGN KEY (`ID_Options`) REFERENCES `options` (`ID`);

--
-- Constraints for table `cities`
--
ALTER TABLE `cities`
    ADD CONSTRAINT `cities_ibfk_1` FOREIGN KEY (`ID_Countries`) REFERENCES `countries` (`ID`);

--
-- Constraints for table `contracts`
--
ALTER TABLE `contracts`
    ADD CONSTRAINT `contracts_ibfk_1` FOREIGN KEY (`ID_Cars`) REFERENCES `cars` (`ID`),
    ADD CONSTRAINT `contracts_ibfk_2` FOREIGN KEY (`ID_Contract_Type`) REFERENCES `contract_types` (`ID`),
    ADD CONSTRAINT `contracts_ibfk_3` FOREIGN KEY (`ID_Orders`) REFERENCES `orders` (`ID`);

--
-- Constraints for table `contract_insurances`
--
ALTER TABLE `contract_insurances`
    ADD CONSTRAINT `contract_insurances_ibfk_1` FOREIGN KEY (`ID_Contract`) REFERENCES `contracts` (`ID`),
    ADD CONSTRAINT `contract_insurances_ibfk_2` FOREIGN KEY (`ID_Insurance`) REFERENCES `insurances` (`ID`);

--
-- Constraints for table `models`
--
ALTER TABLE `models`
    ADD CONSTRAINT `models_ibfk_1` FOREIGN KEY (`ID_Brands`) REFERENCES `brands` (`ID`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
    ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`ID_Users`) REFERENCES `users` (`ID`);

--
-- Constraints for table `roles_permissions`
--
ALTER TABLE `roles_permissions`
    ADD CONSTRAINT `ID_Permissions` FOREIGN KEY (`ID_Permissions`) REFERENCES `permissions` (`ID`),
    ADD CONSTRAINT `ID_Roles` FOREIGN KEY (`ID_Roles`) REFERENCES `roles` (`ID`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
    ADD CONSTRAINT `RolesID` FOREIGN KEY (`ID_Roles`) REFERENCES `roles` (`ID`);

--
-- Constraints for table `users_ads`
--
ALTER TABLE `users_ads`
    ADD CONSTRAINT `users_ads_ibfk_1` FOREIGN KEY (`ID_Ads`) REFERENCES `ads` (`ID`),
    ADD CONSTRAINT `users_ads_ibfk_2` FOREIGN KEY (`ID_Users`) REFERENCES `users` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
