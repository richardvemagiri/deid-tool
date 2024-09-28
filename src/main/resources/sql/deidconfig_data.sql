/*
SQLyog Community
MySQL - 5.7.31 : Database - deid_app
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

USE `deid_tool`;

/*Table structure for table `deidconfig` */

/*Data for the table `deidconfig` */

insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'99999999','/:ClinicalDocument/:recordTarget/:patientRole/:id/@extension','ID');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'123 Test St','/:ClinicalDocument/:recordTarget/:patientRole/:addr/:streetAddressLine','Addr');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'Westboro','/:ClinicalDocument/:recordTarget/:patientRole/:addr/:city','Addr');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'MA','/:ClinicalDocument/:recordTarget/:patientRole/:addr/:state','Addr');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'01581','/:ClinicalDocument/:recordTarget/:patientRole/:addr/:postalCode','Addr');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'US','/:ClinicalDocument/:recordTarget/:patientRole/:addr/:country','Addr');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'123-456-7890','/:ClinicalDocument/:recordTarget/:patientRole/:telecom/@value','Addr');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'Test','/:ClinicalDocument/:recordTarget/:patientRole/:patient/:name/:given','Name');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'Test','/:ClinicalDocument/:recordTarget/:patientRole/:patient/:name/:family','Name');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'Dr','/:ClinicalDocument/:recordTarget/:patientRole/:patient/:name/:prefix','Name');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'Jr','/:ClinicalDocument/:recordTarget/:patientRole/:patient/:name/:suffix','Name');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'M','/:ClinicalDocument/:recordTarget/:patientRole/:patient/:administrativeGenderCode/@code','Gender');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'Male','/:ClinicalDocument/:recordTarget/:patientRole/:patient/:administrativeGenderCode/@displayName','Gender');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'19890307','/:ClinicalDocument/:recordTarget/:patientRole/:patient/:birthTime/@value','DOB');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'S','/:ClinicalDocument/:recordTarget/:patientRole/:patient/:maritalStatusCode/@code','MaritalStatus');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'Single','/:ClinicalDocument/:recordTarget/:patientRole/:patient/:maritalStatusCode/@displayName','MaritalStatus');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'2106-3','/:ClinicalDocument/:recordTarget/:patientRole/:patient/:raceCode/@code','RaceEthnicity');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'White','/:ClinicalDocument/:recordTarget/:patientRole/:patient/:raceCode/@displayName','RaceEthnicity');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'2111-3','/:ClinicalDocument/:recordTarget/:patientRole/:patient/sdtc:raceCode/@code','RaceEthnicity');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'French','/:ClinicalDocument/:recordTarget/:patientRole/:patient/sdtc:raceCode/@displayName','RaceEthnicity');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'2135-2','/:ClinicalDocument/:recordTarget/:patientRole/:patient/:ethnicGroupCode/@code','RaceEthnicity');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'Hispanic or Latino','/:ClinicalDocument/:recordTarget/:patientRole/:patient/:ethnicGroupCode/@displayName','RaceEthnicity');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'2137-8','/:ClinicalDocument/:recordTarget/:patientRole/:patient/sdtc:ethnicGroupCode/@code','RaceEthnicity');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'Spaniard','/:ClinicalDocument/:recordTarget/:patientRole/:patient/sdtc:ethnicGroupCode/@displayName','RaceEthnicity');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'11111111','/:ClinicalDocument/:component/:structuredBody/:component/:section/:entry/:act/:entryRelationship/:act/:participant/:participantRole/:id/@extension','ID');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'456 Test St','/:ClinicalDocument/:component/:structuredBody/:component/:section/:entry/:act/:entryRelationship/:act/:participant/:participantRole/:addr/:streetAddressLine','Addr');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'Westboro','/:ClinicalDocument/:component/:structuredBody/:component/:section/:entry/:act/:entryRelationship/:act/:participant/:participantRole/:addr/:city','Addr');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'MA','/:ClinicalDocument/:component/:structuredBody/:component/:section/:entry/:act/:entryRelationship/:act/:participant/:participantRole/:addr/:state','Addr');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'01581','/:ClinicalDocument/:component/:structuredBody/:component/:section/:entry/:act/:entryRelationship/:act/:participant/:participantRole/:addr/:postalCode','Addr');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'US','/:ClinicalDocument/:component/:structuredBody/:component/:section/:entry/:act/:entryRelationship/:act/:participant/:participantRole/:addr/:country','Addr');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'987-654-3210','/:ClinicalDocument/:component/:structuredBody/:component/:section/:entry/:act/:entryRelationship/:act/:participant/:participantRole/:telecom/@value','Addr');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'Test123','/:ClinicalDocument/:component/:structuredBody/:component/:section/:entry/:act/:entryRelationship/:act/:participant/:participantRole/:playingEntity/:name/:family','Name');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'Test321','/:ClinicalDocument/:component/:structuredBody/:component/:section/:entry/:act/:entryRelationship/:act/:participant/:participantRole/:playingEntity/:name/:given','Name');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'11223344','/:ClinicalDocument/:component/:structuredBody/:component/:section[:title/text()=\'Insurance Providers\']/:text/:table/:tbody/:tr/:td[4]','ID');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'G11223344','/:ClinicalDocument/:component/:structuredBody/:component/:section[:title/text()=\'Insurance Providers\']/:text/:table/:tbody/:tr/:td[5]','ID');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'Test, Test','/:ClinicalDocument/:component/:structuredBody/:component/:section[:title/text()=\'Insurance Providers\']/:text/:table/:tbody/:tr/:td[6]','Name');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'135 Testing St','/:ClinicalDocument/:component/:structuredBody/:component/:section/:entry/:act/:entryRelationship/:act/:performer/:assignedEntity/:addr/:streetAddressLine','Addr');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'Westboro','/:ClinicalDocument/:component/:structuredBody/:component/:section/:entry/:act/:entryRelationship/:act/:performer/:assignedEntity/:addr/:city','Addr');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'01581','/:ClinicalDocument/:component/:structuredBody/:component/:section/:entry/:act/:entryRelationship/:act/:performer/:assignedEntity/:addr/:postalCode','Addr');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'US','/:ClinicalDocument/:component/:structuredBody/:component/:section/:entry/:act/:entryRelationship/:act/:performer/:assignedEntity/:addr/:country','Addr');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'123-456-7890','/:ClinicalDocument/:component/:structuredBody/:component/:section/:entry/:act/:entryRelationship/:act/:performer/:assignedEntity/:telecom[@use=\'HP\']/@value','Addr');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'123-456-7890','/:ClinicalDocument/:component/:structuredBody/:component/:section/:entry/:act/:entryRelationship/:act/:performer/:assignedEntity/:telecom[@use=\'WP\']/@value','Addr');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'test@test.com','/:ClinicalDocument/:component/:structuredBody/:component/:section/:entry/:act/:entryRelationship/:act/:performer/:assignedEntity/:telecom[contains(@value,\'mailto:\')]/@value','Addr');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'Test123','/:ClinicalDocument/:component/:structuredBody/:component/:section/:entry/:act/:entryRelationship/:act/:performer/:assignedEntity/:assignedPerson/:name/:family','Name');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'Test123','/:ClinicalDocument/:component/:structuredBody/:component/:section/:entry/:act/:entryRelationship/:act/:performer/:assignedEntity/:assignedPerson/:name/:given','Name');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'456 Test St','/:ClinicalDocument/:participant/:associatedEntity/:addr/:streetAddressLine','Addr');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'TestCounty','/:ClinicalDocument/:participant/:associatedEntity/:addr/:county','Addr');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'Westboro','/:ClinicalDocument/:participant/:associatedEntity/:addr/:city','Addr');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'MA','/:ClinicalDocument/:participant/:associatedEntity/:addr/:state','Addr');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'01581','/:ClinicalDocument/:participant/:associatedEntity/:addr/:postalCode','Addr');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'US','/:ClinicalDocument/:participant/:associatedEntity/:addr/:country','Addr');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'123-456-7890','/:ClinicalDocument/:participant/:associatedEntity/:telecom/@value','Addr');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'Test','/:ClinicalDocument/:participant/:associatedEntity/:associatedPerson/:name/:given','Name');
insert  into `deidconfig`(`delflag`,`value`,`xpath`,`category`) values (0,'Test','/:ClinicalDocument/:participant/:associatedEntity/:associatedPerson/:name/:family','Name');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
