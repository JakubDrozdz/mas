--certificates
INSERT INTO MAS.dbo.certificate (certificate_name, organization_name, qualifications_description) VALUES (N'PADI OWD', N'Professional Association of Diving Instructors', N'Nurkowanie do 18 metrów wraz z partnerem o uprawnieniach OWD');
INSERT INTO MAS.dbo.certificate (certificate_name, organization_name, qualifications_description) VALUES (N'PADI AOWD - Advanced Open Diver', N'Professional Association of Diving Instructors', N'Nurkowanie do 30 metrów wraz z partnerem');
INSERT INTO MAS.dbo.certificate (certificate_name, organization_name, qualifications_description) VALUES (N'PADI SD - Scuba Diver', N'Professional Association of Diving Instructors', N'Nurkowanie do 12 metrów wraz z divemasterem lub instruktorem');
INSERT INTO MAS.dbo.certificate (certificate_name, organization_name, qualifications_description) VALUES (N'Divemaster', N'Professional Association of Diving Instructors', N'Nurkowanie do 40 metrów. Nadzorowanie i prowadzenie samodzielne nurkowania dla certyfikowanych pletwonurków');

--course types
INSERT INTO MAS.dbo.course_type (max_participant_age, min_participant_age, name, granted_certificate, required_certificate) VALUES (70, 18, N'PADI OWD - Regular', 1, null);
INSERT INTO MAS.dbo.course_type (max_participant_age, min_participant_age, name, granted_certificate, required_certificate) VALUES (17, 14, N'PADI OWD - Junior', 1, null);
INSERT INTO MAS.dbo.course_type (max_participant_age, min_participant_age, name, granted_certificate, required_certificate) VALUES (17, 14, N'PADI AOWD - Regular', 2, 1);
INSERT INTO MAS.dbo.course_type (max_participant_age, min_participant_age, name, granted_certificate, required_certificate) VALUES (70, 14, N'PADI SD - General', 3, null);

--diving courses
INSERT INTO MAS.dbo.diving_course (discriminator, additional_cost, course_status, detailed_description, end_date, start_date, course_instructor, course_type) VALUES (N'single', 0, 3, N'Test diving course 1', N'2024-05-25', N'2024-05-30', null, 4);
INSERT INTO MAS.dbo.diving_course (discriminator, additional_cost, course_status, detailed_description, end_date, start_date, course_instructor, course_type) VALUES (N'single', 150, 2, N'Test diving course 2', N'2024-06-10', N'2024-06-15', null, 2);
INSERT INTO MAS.dbo.diving_course (discriminator, additional_cost, course_status, detailed_description, end_date, start_date, course_instructor, course_type) VALUES (N'pair', 100, 1, N'Test diving course 3', N'2024-06-17', N'2024-06-24', null, 2);
INSERT INTO MAS.dbo.diving_course (discriminator, additional_cost, course_status, detailed_description, end_date, start_date, course_instructor, course_type) VALUES (N'group', 100, 1, N'Test diving course 4', N'2024-06-20', N'2024-06-28', null, 2);
INSERT INTO MAS.dbo.diving_course (discriminator, additional_cost, course_status, detailed_description, end_date, start_date, course_instructor, course_type) VALUES (N'group', 50, 0, N'Test diving course 5', N'2024-07-04', N'2024-07-09', null, 1);
INSERT INTO MAS.dbo.diving_course (discriminator, additional_cost, course_status, detailed_description, end_date, start_date, course_instructor, course_type) VALUES (N'single', 300, 0, N'Test diving course 6', N'2024-07-05', N'2024-07-10', null, 3);
