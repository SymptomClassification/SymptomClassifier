import re
import json
import requests
import sys

subchapters_dict = []
chapters_dict = []
chaptersId = set()
subchaptersId = set()
def classify(chapters, subchapters, subtitles, symptom):

    main_chapters = set()
    symptom_subtitles = set()
    symptom_subchapters = set()
    chapter_indexes = set()
    different_chapters = True
    #  case 1: chapter/subtitle found
    for s_list in chapters:
        for chapter in s_list["name"].split(" "):
            if chapter != " " and chapter != "":
                if re.search(chapter, symptom, re.IGNORECASE):
                    if chapter == "inner" or chapter == "outer" or chapter == "internal" or chapter == "external":
                        if s_list["name"].split(" ")[0] not in symptom:
                            continue
                    main_chapters.add(chapter)
                    chapter_indexes.add(s_list["id"])
                    chapters_dict.append({"id": s_list["id"], "name": s_list["name"].strip()})
                    chaptersId.add(s_list["id"])

    for s_list in subtitles:
        if s_list["name"] != " " and s_list["name"] != "":
            if re.search(s_list["name"], symptom, re.IGNORECASE):
                symptom_subtitles.add(s_list["name"])
                chapter_indexes.add(s_list["id"])
                chapter_names = findChapter(chapter_indexes, chapters)
                if chapter_names:
                    for name in chapter_names:
                        main_chapters.add(name)
                        chapters_dict.append({"id": s_list["chapterId"], "name": name})

    if len(main_chapters) >= 3:
        different_chapters = True
    else:
        for s_list in subchapters:
            if s_list["name"] != " " and s_list["name"] != "":
                if re.search(s_list["name"], symptom, re.IGNORECASE):
                    symptom_subchapters.add(s_list["name"])
                    chapter_indexes.add(s_list["chapterId"])
                    subchaptersId.add(s_list["id"])
                    subchapters_dict.append({"id": s_list["chapterId"], "name": s_list["name"]})
                    if len(main_chapters) == 0:
                        chapter_names = findChapter(chapter_indexes, chapters)
                        if chapter_names:
                            for name in chapter_names:
                                main_chapters.add(name.strip())
                                chapters_dict.append({"id": s_list["chapterId"], "name": name})


    if len(chapter_indexes) != 0 and len(symptom_subchapters) == 0:
        for s_list in subchapters:
            if s_list["chapterId"] in chapter_indexes:
                if "General" == s_list["name"]:
                    symptom_subchapters.add("General")
                    subchapters_dict.append({"id": s_list["chapterId"], "name": "General"})
                    subchaptersId.add(s_list["id"])

    if len(main_chapters) == 0:
        main_chapters.add("unknown")

    if len(symptom_subchapters) > 0:
        main_chapters = match_subchapters(symptom_subchapters, main_chapters, subchapters, chapters)


    return list(main_chapters), list(symptom_subchapters)


def findChapter(chapter_index, chapters):
    titles = []
    for index in chapter_index:
        for chapter in chapters:
            if chapter["id"] == index:
                titles.append(chapter["name"].strip())
                chaptersId.add(chapter["id"])
    for t in titles:
        if t == "" or t == " ":
            titles.remove(t)


    return titles


def match_subchapters(subchapters, chapters, subchapters_origdict, chapters_origdict):
    subchapters_set = list(subchapters)
    chapters_set = list(chapters)
    ids = set()

    for s_list in subchapters_origdict:
        for subchapter_set in subchapters_set:
            if re.search(s_list["name"], subchapter_set, re.IGNORECASE) and s_list["name"] != "General":
                ids.add(s_list["chapterId"])

    for c_list in chapters_origdict:
        if c_list["id"] in ids:
            chapters_set.append(c_list["name"].strip())
            chapters_dict.append({"id": c_list["id"], "name": c_list["name"].strip()})
            chaptersId.add(c_list["id"])


    for t in chapters_set:
        if t == "" or t == " ":
            chapters_set.remove(t)
    return set(chapters_set)
def pipeline(symptom):

    chapters = {}
    subchapters = {}
    subtitles = {}

    symptom_chapter = []
    symptom_subchapter = []
    final_output = set()

    # chaptersRequest = requests.get('http://dagere.comiles.eu:8090/chapters')
    # if chaptersRequest.status_code == 200:

    chapters = [
        {
            "id": 1,
            "name": "Vertigo "
        },
        {
            "id": 2,
            "name": "Head outer "
        },
        {
            "id": 3,
            "name": "Head inner "
        },
        {
            "id": 4,
            "name": "Eye outer "
        },
        {
            "id": 5,
            "name": "Eye inner "
        },
        {
            "id": 6,
            "name": "Vision "
        },
        {
            "id": 7,
            "name": "Ear outer "
        },
        {
            "id": 8,
            "name": "Ear inner "
        },
        {
            "id": 9,
            "name": "Hearing "
        },
        {
            "id": 10,
            "name": "Nose outer "
        },
        {
            "id": 11,
            "name": "Nose inner "
        },
        {
            "id": 12,
            "name": "Rhinitis "
        },
        {
            "id": 13,
            "name": "Face "
        },
        {
            "id": 14,
            "name": "Mouth outer "
        },
        {
            "id": 15,
            "name": "Mouth inner "
        },
        {
            "id": 16,
            "name": "Tongue "
        },
        {
            "id": 17,
            "name": "Taste "
        },
        {
            "id": 18,
            "name": "Jaws teeth "
        },
        {
            "id": 19,
            "name": "Appetite "
        },
        {
            "id": 20,
            "name": "Thirst "
        },
        {
            "id": 21,
            "name": "Food drink "
        },
        {
            "id": 22,
            "name": "Throat internal "
        },
        {
            "id": 23,
            "name": "Belching "
        },
        {
            "id": 24,
            "name": "Nausea vomiting "
        },
        {
            "id": 25,
            "name": "Indigestion dyspepsia "
        },
        {
            "id": 26,
            "name": "Stomach "
        },
        {
            "id": 27,
            "name": "Abdomen internal "
        },
        {
            "id": 28,
            "name": "Intestine "
        },
        {
            "id": 29,
            "name": "Anus perineum "
        },
        {
            "id": 30,
            "name": "Stool "
        },
        {
            "id": 31,
            "name": "Abdomen external "
        },
        {
            "id": 32,
            "name": "Groin pubic region "
        }
    ]

    # subchaptersRequest = requests.get('http://dagere.comiles.eu:8094/subchapters')
    # if subchaptersRequest.status_code == 200:

    subchapters = [{
        "id": 1,
        "name": "General",
        "chapterId": 2
    },
        {
            "id": 2,
            "name": "Temples",
            "chapterId": 2
        },
        {
            "id": 3,
            "name": "Sides",
            "chapterId": 2
        },
        {
            "id": 4,
            "name": "Vertex, parietal region",
            "chapterId": 2
        },
        {
            "id": 5,
            "name": "Occiput",
            "chapterId": 2
        },
        {
            "id": 6,
            "name": "Scalp",
            "chapterId": 2
        },
        {
            "id": 7,
            "name": "Hair",
            "chapterId": 2
        },
        {
            "id": 8,
            "name": "Head, movements of",
            "chapterId": 2
        },
        {
            "id": 9,
            "name": "General",
            "chapterId": 3
        },
        {
            "id": 10,
            "name": "Forehead",
            "chapterId": 3
        },
        {
            "id": 11,
            "name": "Temples",
            "chapterId": 3
        },
        {
            "id": 12,
            "name": "Sides, one -sided",
            "chapterId": 3
        },
        {
            "id": 13,
            "name": "Occiput",
            "chapterId": 3
        },
        {
            "id": 14,
            "name": "Vertex, Parietal bone",
            "chapterId": 3
        },
        {
            "id": 15,
            "name": "Brain",
            "chapterId": 3
        },
        {
            "id": 16,
            "name": "General",
            "chapterId": 4
        },
        {
            "id": 17,
            "name": "Pupils",
            "chapterId": 4
        },
        {
            "id": 18,
            "name": "Conjunctiva",
            "chapterId": 4
        },
        {
            "id": 19,
            "name": "Cornea",
            "chapterId": 4
        },
        {
            "id": 20,
            "name": "Eyelids",
            "chapterId": 4
        },
        {
            "id": 21,
            "name": "- upper",
            "chapterId": 4
        },
        {
            "id": 22,
            "name": "Eyelid margins",
            "chapterId": 4
        },
        {
            "id": 23,
            "name": "Corner of the eyes",
            "chapterId": 4
        },
        {
            "id": 24,
            "name": "- outer",
            "chapterId": 4
        },
        {
            "id": 25,
            "name": "Eyebrows",
            "chapterId": 4
        },
        {
            "id": 26,
            "name": "Eye surrounding",
            "chapterId": 4
        },
        {
            "id": 27,
            "name": "Tears",
            "chapterId": 4
        },
        {
            "id": 28,
            "name": "General",
            "chapterId": 5
        },
        {
            "id": 29,
            "name": "Orbit",
            "chapterId": 5
        },
        {
            "id": 30,
            "name": "Eyeball",
            "chapterId": 5
        },
        {
            "id": 31,
            "name": "Retina",
            "chapterId": 5
        },
        {
            "id": 32,
            "name": "General",
            "chapterId": 7
        },
        {
            "id": 33,
            "name": "Auricles  & Earlobe s",
            "chapterId": 7
        },
        {
            "id": 34,
            "name": "Auditory meatus",
            "chapterId": 7
        },
        {
            "id": 35,
            "name": "Ear, surrounding region",
            "chapterId": 7
        },
        {
            "id": 36,
            "name": "General",
            "chapterId": 8
        },
        {
            "id": 37,
            "name": "Otitis",
            "chapterId": 8
        },
        {
            "id": 38,
            "name": "Discharge",
            "chapterId": 8
        },
        {
            "id": 39,
            "name": "General",
            "chapterId": 10
        },
        {
            "id": 40,
            "name": "Nasal wings",
            "chapterId": 10
        },
        {
            "id": 41,
            "name": "Nostrils",
            "chapterId": 10
        },
        {
            "id": 42,
            "name": "Tip of nose",
            "chapterId": 10
        },
        {
            "id": 43,
            "name": "Nose root",
            "chapterId": 10
        },
        {
            "id": 44,
            "name": "Nose, surrounding region",
            "chapterId": 10
        },
        {
            "id": 45,
            "name": "General",
            "chapterId": 11
        },
        {
            "id": 46,
            "name": "Nasal septum",
            "chapterId": 11
        },
        {
            "id": 47,
            "name": "Nosebleeds",
            "chapterId": 11
        },
        {
            "id": 48,
            "name": "Secretion",
            "chapterId": 11
        },
        {
            "id": 49,
            "name": "Crusts",
            "chapterId": 11
        },
        {
            "id": 50,
            "name": "Smell from the nose",
            "chapterId": 11
        },
        {
            "id": 51,
            "name": "Sneezing",
            "chapterId": 11
        },
        {
            "id": 52,
            "name": "Smelling",
            "chapterId": 11
        },
        {
            "id": 53,
            "name": "General",
            "chapterId": 12
        },
        {
            "id": 54,
            "name": "Runny nose",
            "chapterId": 12
        },
        {
            "id": 55,
            "name": "Sticky rhinitis",
            "chapterId": 12
        },
        {
            "id": 56,
            "name": "Secretion",
            "chapterId": 12
        },
        {
            "id": 57,
            "name": "General",
            "chapterId": 13
        },
        {
            "id": 58,
            "name": "Forehead",
            "chapterId": 13
        },
        {
            "id": 59,
            "name": "Temples",
            "chapterId": 13
        },
        {
            "id": 60,
            "name": "Parotid gland",
            "chapterId": 13
        },
        {
            "id": 61,
            "name": "Cheeks",
            "chapterId": 13
        },
        {
            "id": 62,
            "name": "Beard",
            "chapterId": 13
        },
        {
            "id": 63,
            "name": "Chin",
            "chapterId": 13
        },
        {
            "id": 64,
            "name": "General",
            "chapterId": 14
        },
        {
            "id": 65,
            "name": "Corner of the mouth",
            "chapterId": 14
        },
        {
            "id": 66,
            "name": "Lips",
            "chapterId": 14
        },
        {
            "id": 67,
            "name": "- upper",
            "chapterId": 14
        },
        {
            "id": 68,
            "name": "Mouth, surrounding region",
            "chapterId": 14
        },
        {
            "id": 69,
            "name": "General",
            "chapterId": 15
        },
        {
            "id": 70,
            "name": "inner cheeks",
            "chapterId": 15
        },
        {
            "id": 71,
            "name": "Oral cavity",
            "chapterId": 15
        },
        {
            "id": 72,
            "name": "Palate",
            "chapterId": 15
        },
        {
            "id": 73,
            "name": "Soft palate, uvula",
            "chapterId": 15
        },
        {
            "id": 74,
            "name": "Saliva",
            "chapterId": 15
        },
        {
            "id": 75,
            "name": "- increase",
            "chapterId": 15
        },
        {
            "id": 76,
            "name": "Bad breath",
            "chapterId": 15
        },
        {
            "id": 77,
            "name": "General",
            "chapterId": 16
        },
        {
            "id": 78,
            "name": "Tongue coating",
            "chapterId": 16
        },
        {
            "id": 79,
            "name": "General",
            "chapterId": 17
        },
        {
            "id": 80,
            "name": "Subtle",
            "chapterId": 17
        },
        {
            "id": 81,
            "name": "Weak/ lost",
            "chapterId": 17
        },
        {
            "id": 82,
            "name": "Changed",
            "chapterId": 17
        },
        {
            "id": 83,
            "name": "General",
            "chapterId": 18
        },
        {
            "id": 84,
            "name": "Upper  jaw",
            "chapterId": 18
        },
        {
            "id": 85,
            "name": "Lower  jaw",
            "chapterId": 18
        },
        {
            "id": 86,
            "name": "Temporomandibular joint",
            "chapterId": 18
        },
        {
            "id": 87,
            "name": "Gums",
            "chapterId": 18
        },
        {
            "id": 88,
            "name": "Teeth",
            "chapterId": 18
        },
        {
            "id": 89,
            "name": "- molar teeth",
            "chapterId": 18
        },
        {
            "id": 90,
            "name": "molar teeth",
            "chapterId": 18
        },
        {
            "id": 91,
            "name": "General",
            "chapterId": 19
        },
        {
            "id": 92,
            "name": "Loss of appetite",
            "chapterId": 19
        },
        {
            "id": 93,
            "name": "Hunger",
            "chapterId": 19
        },
        {
            "id": 94,
            "name": "Cravings",
            "chapterId": 19
        },
        {
            "id": 95,
            "name": "General (e.g. desire to drink without thirst)",
            "chapterId": 20
        },
        {
            "id": 96,
            "name": "Thirstlessness",
            "chapterId": 20
        },
        {
            "id": 97,
            "name": "General",
            "chapterId": 21
        },
        {
            "id": 98,
            "name": "Aversion to",
            "chapterId": 21
        },
        {
            "id": 99,
            "name": "Craving for",
            "chapterId": 21
        },
        {
            "id": 100,
            "name": "Aggravation by or intolerable",
            "chapterId": 21
        },
        {
            "id": 101,
            "name": "Improvement through",
            "chapterId": 21
        },
        {
            "id": 102,
            "name": "General",
            "chapterId": 24
        },
        {
            "id": 103,
            "name": "Type of vomit",
            "chapterId": 24
        },
        {
            "id": 104,
            "name": "General",
            "chapterId": 26
        },
        {
            "id": 105,
            "name": "Heartburn",
            "chapterId": 26
        },
        {
            "id": 106,
            "name": "Hiccups",
            "chapterId": 26
        },
        {
            "id": 107,
            "name": "General",
            "chapterId": 27
        },
        {
            "id": 108,
            "name": "Peritoneum, abdominal mesh",
            "chapterId": 27
        },
        {
            "id": 109,
            "name": "Mesentery",
            "chapterId": 27
        },
        {
            "id": 110,
            "name": "Upper abdomen",
            "chapterId": 27
        },
        {
            "id": 111,
            "name": "- Liver",
            "chapterId": 27
        },
        {
            "id": 112,
            "name": "- Spleen",
            "chapterId": 27
        },
        {
            "id": 113,
            "name": "Hypochondria",
            "chapterId": 27
        },
        {
            "id": 114,
            "name": "navel",
            "chapterId": 27
        },
        {
            "id": 115,
            "name": "Umbilicus",
            "chapterId": 27
        },
        {
            "id": 116,
            "name": "Sides of abdomen",
            "chapterId": 27
        },
        {
            "id": 117,
            "name": "Lower abdomen, small pelvis",
            "chapterId": 27
        },
        {
            "id": 118,
            "name": "General",
            "chapterId": 28
        },
        {
            "id": 119,
            "name": "Duodenum",
            "chapterId": 28
        },
        {
            "id": 120,
            "name": "Small intestine",
            "chapterId": 28
        },
        {
            "id": 121,
            "name": "Large intestine",
            "chapterId": 28
        },
        {
            "id": 122,
            "name": "- Colon",
            "chapterId": 28
        },
        {
            "id": 123,
            "name": "Flatulence",
            "chapterId": 28
        },
        {
            "id": 124,
            "name": "- Flatulenc y",
            "chapterId": 28
        },
        {
            "id": 125,
            "name": "anus",
            "chapterId": 29
        },
        {
            "id": 126,
            "name": "General",
            "chapterId": 29
        },
        {
            "id": 127,
            "name": "Perineum",
            "chapterId": 29
        },
        {
            "id": 128,
            "name": "Hemorrhoids",
            "chapterId": 29
        },
        {
            "id": 129,
            "name": "General",
            "chapterId": 30
        },
        {
            "id": 130,
            "name": "u rge to defecate",
            "chapterId": 30
        },
        {
            "id": 131,
            "name": "Rectal tenesmus",
            "chapterId": 30
        },
        {
            "id": 132,
            "name": "trigger",
            "chapterId": 30
        },
        {
            "id": 133,
            "name": "Diarrhea",
            "chapterId": 30
        },
        {
            "id": 134,
            "name": "Constipation",
            "chapterId": 30
        },
        {
            "id": 135,
            "name": "Stool consistency",
            "chapterId": 30
        },
        {
            "id": 136,
            "name": "- Constipation",
            "chapterId": 30
        },
        {
            "id": 137,
            "name": "General",
            "chapterId": 31
        },
        {
            "id": 138,
            "name": "Upper abdomen",
            "chapterId": 31
        },
        {
            "id": 139,
            "name": "Sides",
            "chapterId": 31
        },
        {
            "id": 140,
            "name": "Ribs",
            "chapterId": 31
        },
        {
            "id": 141,
            "name": "Umbilicus",
            "chapterId": 31
        },
        {
            "id": 142,
            "name": "Abdominal wall",
            "chapterId": 31
        },
        {
            "id": 143,
            "name": "Groin",
            "chapterId": 32
        },
        {
            "id": 144,
            "name": "Pubic bone",
            "chapterId": 32
        },
        {
            "id": 145,
            "name": "Lap",
            "chapterId": 32
        }
    ]

    # subtitlesRequest = requests.get('http://dagere.comiles.eu:8098/subtitles')
    # if subtitlesRequest.status_code == 200:
    subtitles = [
        {
            "id": 1,
            "chapterId": 1,
            "name": "dizziness"
        },
        {
            "id": 2,
            "chapterId": 1,
            "name": "giddiness"
        },
        {
            "id": 3,
            "chapterId": 1,
            "name": "waving"
        },
        {
            "id": 4,
            "chapterId": 1,
            "name": "whirling"
        },
        {
            "id": 5,
            "chapterId": 1,
            "name": "tottering"
        },
        {
            "id": 6,
            "chapterId": 1,
            "name": "reeling"
        },
        {
            "id": 7,
            "chapterId": 1,
            "name": "stagger"
        },
        {
            "id": 8,
            "chapterId": 1,
            "name": "sway"
        },
        {
            "id": 9,
            "chapterId": 1,
            "name": "grogginess"
        },
        {
            "id": 10,
            "chapterId": 1,
            "name": "tendency to fall"
        },
        {
            "id": 11,
            "chapterId": 2,
            "name": "cranial bones"
        },
        {
            "id": 12,
            "chapterId": 2,
            "name": "lymph nodes head"
        },
        {
            "id": 13,
            "chapterId": 2,
            "name": "fontanelles"
        },
        {
            "id": 14,
            "chapterId": 2,
            "name": "mastoid"
        },
        {
            "id": 15,
            "chapterId": 2,
            "name": "head lice"
        },
        {
            "id": 16,
            "chapterId": 2,
            "name": "movement involuntary"
        },
        {
            "id": 17,
            "chapterId": 2,
            "name": "rolling"
        },
        {
            "id": 18,
            "chapterId": 2,
            "name": "lifting the head"
        },
        {
            "id": 19,
            "chapterId": 2,
            "name": "holding"
        },
        {
            "id": 20,
            "chapterId": 3,
            "name": "head noise"
        },
        {
            "id": 21,
            "chapterId": 3,
            "name": "migraine"
        },
        {
            "id": 22,
            "chapterId": 3,
            "name": "meningitis"
        },
        {
            "id": 23,
            "chapterId": 3,
            "name": "encephalitis"
        },
        {
            "id": 24,
            "chapterId": 3,
            "name": "apoplexy"
        },
        {
            "id": 25,
            "chapterId": 3,
            "name": "brain tumor"
        },
        {
            "id": 26,
            "chapterId": 4,
            "name": "blepharitis"
        },
        {
            "id": 27,
            "chapterId": 5,
            "name": "cataract"
        },
        {
            "id": 28,
            "chapterId": 5,
            "name": "glaucoma"
        },
        {
            "id": 29,
            "chapterId": 6,
            "name": "squint"
        },
        {
            "id": 30,
            "chapterId": 6,
            "name": "photophobia"
        },
        {
            "id": 31,
            "chapterId": 7,
            "name": "behind the ears"
        },
        {
            "id": 32,
            "chapterId": 7,
            "name": "drilling with fingers"
        },
        {
            "id": 33,
            "chapterId": 8,
            "name": "eustachian tube"
        },
        {
            "id": 34,
            "chapterId": 9,
            "name": "noise"
        },
        {
            "id": 35,
            "chapterId": 9,
            "name": "tinnitus"
        },
        {
            "id": 36,
            "chapterId": 9,
            "name": "hypersensitivity"
        },
        {
            "id": 37,
            "chapterId": 9,
            "name": "deafness"
        },
        {
            "id": 38,
            "chapterId": 11,
            "name": "sinuses"
        },
        {
            "id": 39,
            "chapterId": 11,
            "name": "nasal bones"
        },
        {
            "id": 40,
            "chapterId": 11,
            "name": "sniffing"
        },
        {
            "id": 41,
            "chapterId": 11,
            "name": "snorting"
        },
        {
            "id": 42,
            "chapterId": 11,
            "name": "drilling with fingers"
        },
        {
            "id": 43,
            "chapterId": 13,
            "name": "zygomatic bone"
        },
        {
            "id": 44,
            "chapterId": 13,
            "name": "frontal bone"
        },
        {
            "id": 45,
            "chapterId": 15,
            "name": "soor"
        },
        {
            "id": 46,
            "chapterId": 15,
            "name": "stomatitis"
        },
        {
            "id": 47,
            "chapterId": 15,
            "name": "aphthae"
        },
        {
            "id": 48,
            "chapterId": 15,
            "name": "teething"
        },
        {
            "id": 49,
            "chapterId": 22,
            "name": "pharynx"
        },
        {
            "id": 50,
            "chapterId": 22,
            "name": "tonsils"
        },
        {
            "id": 51,
            "chapterId": 22,
            "name": "pharyngeal tonsils"
        },
        {
            "id": 52,
            "chapterId": 24,
            "name": "gagging"
        },
        {
            "id": 53,
            "chapterId": 24,
            "name": "retching"
        },
        {
            "id": 54,
            "chapterId": 24,
            "name": "nausea"
        },
        {
            "id": 55,
            "chapterId": 24,
            "name": "disgust"
        },
        {
            "id": 56,
            "chapterId": 24,
            "name": "nausea wit h mouth watering"
        },
        {
            "id": 57,
            "chapterId": 24,
            "name": "nausea with"
        },
        {
            "id": 58,
            "chapterId": 24,
            "name": "belching"
        },
        {
            "id": 59,
            "chapterId": 24,
            "name": "seasickness"
        },
        {
            "id": 60,
            "chapterId": 24,
            "name": "motion sickness"
        },
        {
            "id": 61,
            "chapterId": 26,
            "name": "cardia"
        },
        {
            "id": 62,
            "chapterId": 26,
            "name": "esophagus"
        },
        {
            "id": 63,
            "chapterId": 26,
            "name": "pylorus"
        },
        {
            "id": 64,
            "chapterId": 26,
            "name": "stomach inlet/ cardiac opening"
        },
        {
            "id": 65,
            "chapterId": 26,
            "name": "gastritis"
        },
        {
            "id": 66,
            "chapterId": 26,
            "name": "gastroenteritis"
        },
        {
            "id": 67,
            "chapterId": 26,
            "name": "ulcer"
        },
        {
            "id": 68,
            "chapterId": 26,
            "name": "hyperacidity"
        },
        {
            "id": 69,
            "chapterId": 26,
            "name": "singultus"
        },
        {
            "id": 70,
            "chapterId": 26,
            "name": "dyspepsia"
        },
        {
            "id": 71,
            "chapterId": 27,
            "name": "upper abdomen: epigastrium"
        },
        {
            "id": 72,
            "chapterId": 27,
            "name": "pit of stomach"
        },
        {
            "id": 73,
            "chapterId": 27,
            "name": "pit of heart"
        },
        {
            "id": 74,
            "chapterId": 27,
            "name": "hypochondrium: lower rib region"
        },
        {
            "id": 75,
            "chapterId": 27,
            "name": "lower abdomen: hypogastrium"
        },
        {
            "id": 76,
            "chapterId": 27,
            "name": "mesenteric glands"
        },
        {
            "id": 77,
            "chapterId": 27,
            "name": "omentum"
        },
        {
            "id": 78,
            "chapterId": 27,
            "name": "bile ducts"
        },
        {
            "id": 79,
            "chapterId": 27,
            "name": "appendicitis typhlitis"
        },
        {
            "id": 80,
            "chapterId": 27,
            "name": "abdominal grumbling"
        },
        {
            "id": 81,
            "chapterId": 27,
            "name": "abdominal cutting"
        },
        {
            "id": 82,
            "chapterId": 27,
            "name": "ascites"
        },
        {
            "id": 83,
            "chapterId": 27,
            "name": "abdominal ailment ?"
        },
        {
            "id": 84,
            "chapterId": 27,
            "name": "gripes"
        },
        {
            "id": 85,
            "chapterId": 27,
            "name": "stomachache"
        },
        {
            "id": 86,
            "chapterId": 28,
            "name": "bowel"
        },
        {
            "id": 87,
            "chapterId": 28,
            "name": "entrails"
        },
        {
            "id": 88,
            "chapterId": 28,
            "name": "intestine"
        },
        {
            "id": 89,
            "chapterId": 28,
            "name": "Small intestine: duodenum"
        },
        {
            "id": 90,
            "chapterId": 28,
            "name": "jejunum"
        },
        {
            "id": 91,
            "chapterId": 28,
            "name": "ileum"
        },
        {
            "id": 92,
            "chapterId": 28,
            "name": "Ileocecal valve"
        },
        {
            "id": 93,
            "chapterId": 28,
            "name": "Large intestine: appendix cecum"
        },
        {
            "id": 94,
            "chapterId": 28,
            "name": "caecum"
        },
        {
            "id": 95,
            "chapterId": 28,
            "name": "caecum with vermiform appendix"
        },
        {
            "id": 96,
            "chapterId": 28,
            "name": "colon"
        },
        {
            "id": 97,
            "chapterId": 28,
            "name": "rectum  and anal canal"
        },
        {
            "id": 98,
            "chapterId": 28,
            "name": "Ileus intestinal paralysis"
        },
        {
            "id": 99,
            "chapterId": 28,
            "name": "intussusception"
        },
        {
            "id": 100,
            "chapterId": 28,
            "name": "diverticulosis"
        },
        {
            "id": 101,
            "chapterId": 28,
            "name": "proctitis"
        },
        {
            "id": 102,
            "chapterId": 28,
            "name": "distension"
        },
        {
            "id": 103,
            "chapterId": 28,
            "name": "flatulence"
        },
        {
            "id": 104,
            "chapterId": 28,
            "name": "meteorism"
        },
        {
            "id": 105,
            "chapterId": 28,
            "name": "rumbling"
        },
        {
            "id": 106,
            "chapterId": 28,
            "name": "sloshing"
        },
        {
            "id": 107,
            "chapterId": 28,
            "name": "splashing"
        },
        {
            "id": 108,
            "chapterId": 28,
            "name": "irritable"
        },
        {
            "id": 109,
            "chapterId": 28,
            "name": "bowel syndrome"
        },
        {
            "id": 110,
            "chapterId": 29,
            "name": "internal sphincter"
        },
        {
            "id": 111,
            "chapterId": 29,
            "name": "midgut"
        },
        {
            "id": 112,
            "chapterId": 29,
            "name": "anal sphincter"
        },
        {
            "id": 113,
            "chapterId": 29,
            "name": "anal glands"
        },
        {
            "id": 114,
            "chapterId": 29,
            "name": "perineum"
        },
        {
            "id": 115,
            "chapterId": 29,
            "name": "prolapsed bowel"
        },
        {
            "id": 116,
            "chapterId": 29,
            "name": "prolapsed anus"
        },
        {
            "id": 117,
            "chapterId": 29,
            "name": "perineal hernia"
        },
        {
            "id": 118,
            "chapterId": 29,
            "name": "worms"
        },
        {
            "id": 119,
            "chapterId": 29,
            "name": "parasites"
        },
        {
            "id": 120,
            "chapterId": 29,
            "name": "encopresis"
        },
        {
            "id": 121,
            "chapterId": 29,
            "name": "defecation"
        },
        {
            "id": 122,
            "chapterId": 29,
            "name": "tenesmus ani painful urge to defecate"
        },
        {
            "id": 123,
            "chapterId": 30,
            "name": "cholera"
        },
        {
            "id": 124,
            "chapterId": 30,
            "name": "dysentery"
        },
        {
            "id": 125,
            "chapterId": 31,
            "name": "turnout of ribs"
        },
        {
            "id": 126,
            "chapterId": 31,
            "name": "navel"
        },
        {
            "id": 127,
            "chapterId": 31,
            "name": "abdominal muscles"
        },
        {
            "id": 128,
            "chapterId": 31,
            "name": "ribs"
        },
        {
            "id": 129,
            "chapterId": 31,
            "name": "turnout"
        },
        {
            "id": 130,
            "chapterId": 31,
            "name": "flank"
        },
        {
            "id": 131,
            "chapterId": 31,
            "name": "umbilicus"
        },
        {
            "id": 132,
            "chapterId": 32,
            "name": "thigh"
        },
        {
            "id": 133,
            "chapterId": 32,
            "name": "groin"
        },
        {
            "id": 134,
            "chapterId": 32,
            "name": "inguinal glands"
        },
        {
            "id": 135,
            "chapterId": 32,
            "name": "groin"
        },
        {
            "id": 136,
            "chapterId": 32,
            "name": "inner inguinal"
        },
        {
            "id": 137,
            "chapterId": 32,
            "name": "ring abdominal ring"
        },
        {
            "id": 138,
            "chapterId": 32,
            "name": "outer inguinal ring"
        },
        {
            "id": 139,
            "chapterId": 32,
            "name": "inguinal hernia"
        },
        {
            "id": 140,
            "chapterId": 32,
            "name": "pubic bone"
        },
        {
            "id": 141,
            "chapterId": 32,
            "name": "pubic"
        },
        {
            "id": 142,
            "chapterId": 32,
            "name": "mound"
        },
        {
            "id": 143,
            "chapterId": 32,
            "name": "womb lap"
        }
    ]

    symptom_chapter, symptom_subchapter = classify(chapters, subchapters, subtitles, symptom)

    if len(symptom_chapter) == 0:
        symptom_chapter.append("unknown")
    if len(symptom_subchapter) == 0:
        symptom_subchapter.append("unknown")

    if len(chapters_dict) != 0:
        for id in chapters_dict:
            chapter_subchapter = []
            for chapterId in subchapters_dict:
                if id["id"] == chapterId["id"]:
                    chapter_subchapter.append(chapterId["name"])
            if len(chapter_subchapter) != 0:
                final_output.add(id["name"] + ", "+ " , ".join(chapter_subchapter))
            else:
                final_output.add(id["name"])
    if len(final_output) == 0:
        final_output.add("unknown")

    #if len(chaptersId) == 0:
        #chaptersId.add(-1)
    #if len(subchaptersId) == 0:
        #subchaptersId.add(-1)

    return [list(final_output)]

if __name__ == '__main__':
    classification = pipeline(" ".join(sys.argv[1:]))
    print(classification)


