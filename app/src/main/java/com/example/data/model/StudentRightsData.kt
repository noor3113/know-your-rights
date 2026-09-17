package com.example.data.model

data class RightItem(
    val id: String,
    val title: String,
    val explanation: String,
    val source: String,
    val practicalTip: String? = null
)

data class CategoryItem(
    val id: String,
    val name: String,
    val description: String,
    val rights: List<RightItem>
)

object StudentRightsRepository {
    val categories: List<CategoryItem> = listOf(
        CategoryItem(
            id = "right_to_learn",
            name = "Right to Learn",
            description = "Guaranteed access to free public education, academic accommodations, and open inquiry.",
            rights = listOf(
                RightItem(
                    id = "learn_1",
                    title = "Free and Equal Public Education",
                    explanation = "Every child residing in the United States, regardless of immigration status, citizenship, native language, or family income, has an unconditional constitutional right to attend tuition-free public elementary and secondary schools. School districts are strictly prohibited from asking for social security cards or reporting immigration status to external agencies.",
                    source = "Plyler v. Doe, 457 U.S. 202 (1982) & 14th Amendment Equal Protection Clause",
                    practicalTip = "Schools cannot deny enrollment because of missing residency documents or immigration status."
                ),
                RightItem(
                    id = "learn_2",
                    title = "Special Education & Individualized Accommodations",
                    explanation = "Students with physical, emotional, sensory, cognitive, or learning disabilities have a federal right to a Free Appropriate Public Education (FAPE) tailored specifically to their needs in the Least Restrictive Environment (LRE). Public schools must formulate, fund, and adhere to enforceable Individualized Education Programs (IEPs) or Section 504 plans.",
                    source = "Individuals with Disabilities Education Act (IDEA), 20 U.S.C. § 1400 & Section 504 of Rehabilitation Act",
                    practicalTip = "You can request an evaluation for special accommodations at any time in writing."
                ),
                RightItem(
                    id = "learn_3",
                    title = "Classroom Freedom of Inquiry & Viewpoint Diversity",
                    explanation = "Students maintain First Amendment protections inside academic settings. While teachers may preserve order, school authorities cannot censor student speech or ban reading materials simply because administrators or community members disagree with political, social, or philosophical viewpoints expressed.",
                    source = "Keyishian v. Board of Regents, 385 U.S. 589 (1967) & Board of Education v. Pico, 457 U.S. 853 (1982)",
                    practicalTip = "Expressing controversial opinions during class discussions is protected as long as it does not disrupt teaching."
                ),
                RightItem(
                    id = "learn_4",
                    title = "Language Assistance for English Learners",
                    explanation = "Students who are learning English cannot be left to struggle without specialized support. Public school districts are required by federal civil rights laws to provide qualified English Language Learner (ELL) instruction and translate school notifications for non-English speaking parents.",
                    source = "Lau v. Nichols, 414 U.S. 563 (1974) & Equal Educational Opportunities Act, 20 U.S.C. § 1703",
                    practicalTip = "Parents have a right to receive important school communications in their native language."
                ),
                RightItem(
                    id = "learn_5",
                    title = "Access to Essential Instructional Materials",
                    explanation = "All enrolled students have a right to standard curriculum textbooks, instructional laboratory supplies, and safe learning facilities. Public schools cannot charge mandatory fees for required courses or withhold grades due to unpaid textbook or lab supply balances.",
                    source = "Williams v. State of California & State Constitutional Free School Clauses",
                    practicalTip = "Mandatory class curriculum materials must be provided without charging required fees."
                )
            )
        ),
        CategoryItem(
            id = "right_to_privacy",
            name = "Right to Privacy",
            description = "Protections covering student educational records, backpacks, lockers, and mobile phones.",
            rights = listOf(
                RightItem(
                    id = "privacy_1",
                    title = "Student Educational Records Confidentiality (FERPA)",
                    explanation = "Your educational files—including grade transcripts, psychological assessments, attendance logs, and disciplinary files—are confidential under federal law. Schools cannot disclose these records to unauthorized third parties or law enforcement without parental consent or student consent (if 18+).",
                    source = "Family Educational Rights and Privacy Act (FERPA), 34 CFR Part 99 & 20 U.S.C. § 1232g",
                    practicalTip = "You or your parents can request to view and inspect all educational records in your file."
                ),
                RightItem(
                    id = "privacy_2",
                    title = "Protection Against Unreasonable Searches",
                    explanation = "School administrators cannot search your body, pockets, or clothes on a vague suspicion or generalized hunch. To conduct a search, school officials must have 'reasonable suspicion' specific to you that a search will reveal evidence of a broken school rule or law. Invasive strip searches are strictly prohibited.",
                    source = "New Jersey v. T.L.O., 469 U.S. 325 (1985) & Safford Unified School District v. Redding, 557 U.S. 364 (2009)",
                    practicalTip = "You can clearly state: 'I do not consent to this search,' while remaining respectful and calm."
                ),
                RightItem(
                    id = "privacy_3",
                    title = "Cell Phone & Personal Digital Device Privacy",
                    explanation = "Even if school policy forbids phone use in class, confiscating your phone does NOT grant school officials automatic authority to unlock it, read private texts, check social media accounts, or inspect photos without a specific, justified reason linking the phone's contents to a serious safety threat or violation.",
                    source = "Riley v. California, 573 U.S. 373 (2014) & Fourth Amendment Jurisprudence",
                    practicalTip = "School staff cannot demand your personal social media passwords without legal cause."
                ),
                RightItem(
                    id = "privacy_4",
                    title = "Locker & Backpack Search Safeguards",
                    explanation = "While school lockers are campus property that administrators can inspect for general safety hazards, targeted searches of your personal backpack, purse, or locked gym bag require individualized reasonable suspicion that prohibited items are hidden inside.",
                    source = "U.S. Constitution, Fourth Amendment & State Student Rights Regulations",
                    practicalTip = "Personal containers inside lockers maintain higher privacy expectations than the locker itself."
                ),
                RightItem(
                    id = "privacy_5",
                    title = "Medical and Psychological Care Confidentiality",
                    explanation = "Records of visits to school nurses, adolescent health screenings, psychological counseling notes, and medication administration are protected health details. School staff cannot disclose private medical diagnoses to unauthorized personnel or classmates.",
                    source = "HIPAA Privacy Standards & FERPA Health Records Regulation 34 CFR § 99.36",
                    practicalTip = "Visits to the school nurse or mental health counselor cannot be made public to classmates."
                )
            )
        ),
        CategoryItem(
            id = "right_to_fair_treatment",
            name = "Right to Fair Treatment",
            description = "Guaranteed due process in discipline, protection against bias, and free expression.",
            rights = listOf(
                RightItem(
                    id = "treatment_1",
                    title = "Due Process in Suspensions and Expulsions",
                    explanation = "Before any out-of-school suspension, school officials MUST give you notice of the allegations, review the evidence against you, and provide an opportunity for you to tell your side of the story. For suspensions exceeding 10 days or expulsions, formal hearings with parental notice and right to representation are mandatory.",
                    source = "Goss v. Lopez, 419 U.S. 565 (1975) & U.S. Const. amend. XIV Due Process Clause",
                    practicalTip = "Always ask for written notice of the infractions and request an opportunity to explain your side."
                ),
                RightItem(
                    id = "treatment_2",
                    title = "Freedom of Peaceful Expression & Speech",
                    explanation = "Students do not surrender their constitutional rights to freedom of speech or expression at the schoolhouse gate. You have the right to wear symbolic buttons, armbands, cultural clothing, and express personal beliefs as long as it does not cause a substantial and material disruption to school activities.",
                    source = "Tinker v. Des Moines Independent Community School District, 393 U.S. 503 (1969)",
                    practicalTip = "Peaceful protest and symbolic clothing are constitutionally protected when non-disruptive."
                ),
                RightItem(
                    id = "treatment_3",
                    title = "Protection Against Gender & Sex Discrimination (Title IX)",
                    explanation = "Title IX prohibits discrimination on the basis of sex, gender identity, or sexual orientation in federally supported educational institutions. Students are protected from sexual harassment, gender-biased athletic funding, and discriminatory dress codes that penalize specific students disproportionately.",
                    source = "Title IX of the Education Amendments of 1972, 20 U.S.C. § 1681 & OCR Guidance",
                    practicalTip = "Every school must designate an official Title IX Coordinator to receive and investigate complaints."
                ),
                RightItem(
                    id = "treatment_4",
                    title = "Protection from Racial and Ethnic Bias (Title VI)",
                    explanation = "No student may be subjected to differential treatment, harsher disciplinary penalties, or excluded from honors programs on the basis of race, color, or national origin. Schools have an enforceable duty to dismantle discriminatory tracking systems and bias.",
                    source = "Title VI of the Civil Rights Act of 1964, 42 U.S.C. § 2000d",
                    practicalTip = "Report instances of racial hostility or biased disciplinary disparities in writing to the principal."
                ),
                RightItem(
                    id = "treatment_5",
                    title = "Protection Against Bullying and Harassment",
                    explanation = "Schools have an affirmative legal responsibility to protect students from hostile environments caused by persistent physical, verbal, or cyberbullying. School leadership must investigate complaints promptly and enact safety measures to protect targeted students.",
                    source = "U.S. Department of Education Office for Civil Rights (OCR) Harassment Guidance",
                    practicalTip = "Document dates, times, screenshots, and witnesses when reporting bullying incidents."
                )
            )
        ),
        CategoryItem(
            id = "right_to_support",
            name = "Right to Support",
            description = "Access to mental health counselors, nutritional meal programs, and shelter support.",
            rights = listOf(
                RightItem(
                    id = "support_1",
                    title = "Access to Mental Health & Counseling Services",
                    explanation = "Students facing severe emotional stress, anxiety, grief, or behavioral hurdles have the right to request confidential access to credentialed guidance counselors, school social workers, and school psychologists without academic penalties.",
                    source = "Elementary and Secondary Education Act (ESEA) Title IV & Federal Student Support Grants",
                    practicalTip = "You can ask any teacher or staff member for a referral to the school guidance counselor."
                ),
                RightItem(
                    id = "support_2",
                    title = "School Nutrition & Free Meal Guarantees",
                    explanation = "Eligible low-income students are federally guaranteed nutritious breakfast and lunch options through the National School Lunch Program. Schools are legally forbidden from public lunch-shaming, such as throwing away trays or isolating students with meal debt.",
                    source = "Richard B. Russell National School Lunch Act, 42 U.S.C. § 1751 & USDA Regulations",
                    practicalTip = "School lunch debt cannot be used to prevent students from attending graduation ceremonies."
                ),
                RightItem(
                    id = "support_3",
                    title = "Rights for Homeless & Housing-Insecure Youth",
                    explanation = "Under the McKinney-Vento Act, students experiencing housing instability, staying in shelters, or living with relatives have the right to immediate school enrollment without residency records, birth certificates, or immunization paperwork, along with free transportation to their school of origin.",
                    source = "McKinney-Vento Homeless Assistance Act, 42 U.S.C. § 11431 et seq.",
                    practicalTip = "Every school district has a designated Homeless Liaison to arrange free busing and waived fees."
                ),
                RightItem(
                    id = "support_4",
                    title = "Support for Pregnant and Parenting Students",
                    explanation = "Under Title IX, public schools cannot expel, penalize, or coerce pregnant or parenting students into alternative programs. Schools must excuse medically necessary absences without lowering grades and provide private lactation accommodations.",
                    source = "Title IX Regulations, 34 C.F.R. § 106.40(b)",
                    practicalTip = "Schools must allow parenting students to make up missed exams with equal credit."
                ),
                RightItem(
                    id = "support_5",
                    title = "Restorative Support and Conflict Resolution",
                    explanation = "Students facing discipline have the right to request restorative justice mediation, peer counseling, and restorative conferences as constructive alternatives to punitive suspensions, fostering rehabilitation and understanding.",
                    source = "Every Student Succeeds Act (ESSA), 20 U.S.C. § 6301 & Positive Behavioral Support Frameworks",
                    practicalTip = "Ask if your school offers peer mediation or restorative circles before accepting suspension."
                )
            )
        )
    )
}
