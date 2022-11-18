/*
상권 정보 테이블

컬럼은 순서대로,
상가업소번호,상호명,지점명,상권업종대분류코드,상권업종대분류명,상권업종중분류코드,상권업종중분류명,상권업종소분류코드,상권업종소분류명,표준산업분류코드,표준산업분류명,시도코드,시도명,시군구코드,시군구명,행정동코드,행정동명,법정동코드,법정동명,지번코드,대지구분코드,대지구분명,지번본번지,지번부번지,지번주소,도로명코드,도로명,건물본번지,건물부번지,건물관리번호,건물명,도로명주소,구우편번호,신우편번호,동정보,층정보,호정보,경도,위도
*/

CREATE SCHEMA IF NOT EXISTS `happyhouse` DEFAULT CHARACTER SET utf8;

DROP TABLE IF EXISTS `happyhouse`.`commercial`;

CREATE TABLE IF NOT EXISTS `happyhouse`.`commercial` (
    `no` INT NOT NULL,
    `name` VARCHAR(45) NULL,
    `jijum` VARCHAR(45) NULL,
    `category_b_code` VARCHAR(10) NULL,
    `category_b_name` VARCHAR(45) NULL,
    `category_m_code` VARCHAR(10) NULL,
    `category_m_name` VARCHAR(45) NULL,
    `category_s_code` VARCHAR(10) NULL,
    `category_s_name` VARCHAR(45) NULL,
    `std_code` VARCHAR(10) NULL,
    `std_name` VARCHAR(45) NULL,
    `sido_code` VARCHAR(10) NULL,
    `sido_name` VARCHAR(45) NULl,
    `gugun_code` VARCHAR(10) NULL,
    `gugun_name` VARCHAR(45) NULL,
    `dong_h_code` VARCHAR(12) NULl,
    `dong_h_name` VARCHAR(45) NULL,
    `dong_b_code` VARCHAR(12) NULl,
    `dong_b_name` VARCHAR(45) NULL,
    `jibun` VARCHAR(45) NULL,
    `site_code` VARCHAR(10) NULL,
    `site_type` VARCHAR(10) NULL,
    `jibun_1` VARCHAR(10) NULL,
    `jibun_2` VARCHAR(10) NULL,
    `jibun_address` VARCHAR(45) NULL,
    `load_code` VARCHAR(45) NULL,
    `load_name` VARCHAR(45) NULL,
    `building_1_bunji` VARCHAR(10) NULL,
    `building_2_bunji` VARchAR(10) NULL,
    `builidng_code` VARCHAR(45) NULL,
    `building_name` VARCHAR(45) NULL,
    `load_address` VARCHAR(45) NULL,
    `post_old` VARCHAR(10) NULL,
    `post_new` VARCHAR(10) NULL,
    `dong` VARCHAR(45) NULL,
    `floor` VARCHAR(10) NULL,
    `ho` VARCHAR(45) NULL,
    `lng` VARCHAR(45) NULL,
    `lat` VARCHAR(45) NULL,
    PRIMARY KEY (`no`)
) ENGINE = InnoDB;
