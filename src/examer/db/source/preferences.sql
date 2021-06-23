BEGIN TRANSACTION;

-- Table preferences
CREATE TABLE IF NOT EXISTS `preferences` ( 
    `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    `libelle` VARCHAR ( 128 ),
    `value` TEXT
);
-- Content:
INSERT INTO `preferences` (`libelle`, `value`) VALUES 
    ("LANGUAGE", "AR"),
    ("KINGDOM-AR", "المملكة المغربية%new_line%وزارة التربية الوطنية%new_line%والتكوين المهني%new_line%والتعليم العالي والبحث العلمي%new_line%الأكاديمية الجهوية للتربية والتكوين"), 
    ("KINGDOM-TA", "tagldit n lmvrib%new_line%tamawast n usgmi anamur%new_line%d usilv azzulan%new_line%d usmdi anaflla d urezzu amassan%new_line%takadimit tanmnaät n usgmi d usmuttg"), 
    ("TRIAL_TITLE", ""), 
    ("TRIAL_ORDINARY_SESSION", "Y"), 
    ("TRIAL_PRESIDENT_NAME", ""), 
    ("TRIAL_PRESIDENT_CODE", ""), 
    ("TRIAL_OBSERVER_NAME", ""), 
    ("TRIAL_OBSERVER_CODE", ""), 
    ("TRIAL_SECRETARY_NAME", ""), 
    ("TRIAL_SECRETARY_CODE", ""),
    ("BRANCHES_OUTPUT_INCLUDE_ABSENTS", "Y"),
    ("BRANCHES_OUTPUT_CONSIDERE_SFL", "N"),
    ("ROOMS_OUTPUT_INCLUDE_ABSENTS", "Y"),
    ("ROOMS_OUTPUT_CONSIDERE_SFL", "N"),
    ("ROOMS_OUTPUT_DESTINATION_FOLDER", ""),
    ("BRANCHES_OUTPUT_DESTINATION_FOLDER", ""),
    ("TRIALS_OUTPUT_DESTINATION_FOLDER", ""),
    ("TRIALS_OUTPUT_DUPLICATE_PAGES", "Y"),
    ("BRANCHES_OUTPUT_MAX_ROOMS_USED", "N"),
    ("BRANCHES_OUTPUT_MAX_ROOMS", "1"),
    ("ROOMS_OUTPUT_TITLE", "")
;

COMMIT;