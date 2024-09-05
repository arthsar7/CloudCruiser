package com.Cloud.Cruiser.cloudgame.ui
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Cloud.Cruiser.cloudgame.R
import com.Cloud.Cruiser.cloudgame.ui.theme.CloudCruiserTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PrivacyPolicyScreen(
    onBack: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.bg), // Background image
                contentScale = ContentScale.FillBounds
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // Title
        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .paint(
                    painter = painterResource(id = R.drawable.big_btn_bg), // Title background
                    contentScale = ContentScale.FillBounds
                )
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            OutlinedText(
                text = "Privacy\nPolicy",
                textAlign = TextAlign.Center,
                fontSize = 24.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Text content box (Scrollable)
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(0.9f)
                .padding(16.dp)
                .paint(
                    painter = painterResource(id = R.drawable.long_bg), // Box background
                    contentScale = ContentScale.FillBounds
                )
                .padding(16.dp),
            contentAlignment = Alignment.TopStart
        ) {
            // Scrollable content
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                OutlinedText(
                    text = "Privacy Policy\n" +
                            "This privacy policy applies to the Cloud Cruiser app (hereby referred to as “Application”) for mobile devices that was created by (hereby referred to as “Service Provider”) as a Free service. This service is intended for use “AS IS”.\n" +
                            "\n" +
                            "Information Collection and Use\n" +
                            "The Application collects information when you download and use it. This information may include information such as\n" +
                            "\n" +
                            "Your device's Internet Protocol address (e.g. IP address)\n" +
                            "The pages of the Application that you visit, the time and date of your visit, the time spent on those pages\n" +
                            "The time spent on the Application\n" +
                            "The operating system you use on your mobile device\n" +
                            "\n" +
                            "The Application does not gather precise information about the location of your mobile device.\n" +
                            "\n" +
                            "The Service Provider may use the information you provided to contact you from time to time to provide you with important information, required notices and marketing promotions.\n" +
                            "\n" +
                            "For a better experience, while using the Application, the Service Provider may require you to provide us with certain personally identifiable information, including but not limited to developer. The information that the Service Provider request will be retained by them and used as described in this privacy policy.\n" +
                            "\n" +
                            "Third Party Access\n" +
                            "Only aggregated, anonymized data is periodically transmitted to external services to aid the Service Provider in improving the Application and their service. The Service Provider may share your information with third parties in the ways that are described in this privacy statement.\n" +
                            "\n" +
                            "Please note that the Application utilizes third-party services that have their own Privacy Policy about handling data. Below are the links to the Privacy Policy of the third-party service providers used by the Application:\n" +
                            "\n" +
                            "Google Play Services\n" +
                            "\n" +
                            "The Service Provider may disclose User Provided and Automatically Collected Information:\n" +
                            "\n" +
                            "as required by law, such as to comply with a subpoena, or similar legal process;\n" +
                            "when they believe in good faith that disclosure is necessary to protect their rights, protect your safety or the safety of others, investigate fraud, or respond to a government request;\n" +
                            "with their trusted services providers who work on their behalf, do not have an independent use of the information we disclose to them, and have agreed to adhere to the rules set forth in this privacy statement.\n" +
                            "\n" +
                            "Opt-Out Rights\n" +
                            "You can stop all collection of information by the Application easily by uninstalling it. You may use the standard uninstall processes as may be available as part of your mobile device or via the mobile application marketplace or network.\n" +
                            "\n" +
                            "Data Retention Policy\n" +
                            "The Service Provider will retain User Provided data for as long as you use the Application and for a reasonable time thereafter. If you'd like them to delete User Provided Data that you have provided via the Application, please contact them at CloudCruiser@developer.net and they will respond in a reasonable time.\n" +
                            "\n" +
                            "Children\n" +
                            "The Service Provider does not use the Application to knowingly solicit data from or market to children under the age of 13.\n" +
                            "\n" +
                            "The Application does not address anyone under the age of 13. The Service Provider does not knowingly collect personally identifiable information from children under 13 years of age. In the case the Service Provider discover that a child under 13 has provided personal information, the Service Provider will immediately delete this from their servers. If you are a parent or guardian and you are aware that your child has provided us with personal information, please contact the Service Provider (CloudCruiser@developer.net) so that they will be able to take the necessary actions.\n" +
                            "\n" +
                            "Security\n" +
                            "The Service Provider is concerned about safeguarding the confidentiality of your information. The Service Provider provides physical, electronic, and procedural safeguards to protect information the Service Provider processes and maintains.\n" +
                            "\n" +
                            "Changes\n" +
                            "This Privacy Policy may be updated from time to time for any reason. The Service Provider will notify you of any changes to the Privacy Policy by updating this page with the new Privacy Policy. You are advised to consult this Privacy Policy regularly for any changes, as continued use is deemed approval of all changes.\n" +
                            "\n" +
                            "This privacy policy is effective as of 2024-09-04\n" +
                            "\n" +
                            "Your Consent\n" +
                            "By using the Application, you are consenting to the processing of your information as set forth in this Privacy Policy now and as amended by us.\n" +
                            "\n" +
                            "Contact Us\n" +
                            "If you have any questions regarding privacy while using the Application, or have questions about the practices, please contact the Service Provider via email at CloudCruiser@developer.net.",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Start
                )
                // Add more text content here for the privacy policy
                // Example long text can go here.
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Back button
        Box(
            modifier = Modifier
                .paint(
                    painter = painterResource(id = R.drawable.small_btn_bg), // Button background
                    contentScale = ContentScale.FillBounds
                )
                .padding(horizontal = 16.dp)
                .clickable(onClick = onBack),
            contentAlignment = Alignment.Center
        ) {
            OutlinedText(
                text = "Back",
                textAlign = TextAlign.Center,
                fontSize = 24.sp
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

    }
}

@Preview
@Composable
fun PrivacyPolicyScreenPreview() {
    CloudCruiserTheme {
        PrivacyPolicyScreen()
    }
}