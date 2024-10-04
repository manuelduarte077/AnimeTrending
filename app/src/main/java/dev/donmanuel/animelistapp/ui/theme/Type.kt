package dev.donmanuel.animelistapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.donmanuel.animelistapp.R


val monseratFamily = FontFamily(
	Font(R.font.montserrat_regular),
	Font(R.font.montserrat_light, FontWeight.W500),
	Font(R.font.montserrat_medium, FontWeight.Medium),
	Font(R.font.montserrat_bold, FontWeight.Bold)
)


// Set of Material typography styles to start with
val Typography = Typography(

	bodyLarge = TextStyle(
		fontFamily = monseratFamily,
		fontWeight = FontWeight.Bold,
		fontSize = 16.sp,
		lineHeight = 24.sp,
		letterSpacing = 0.5.sp
	),

	titleMedium = TextStyle(
		fontFamily = monseratFamily,
		fontWeight = FontWeight.Medium,
		fontSize = 20.sp,
		lineHeight = 28.sp,
		letterSpacing = 0.15.sp
	),

	titleSmall = TextStyle(
		fontFamily = monseratFamily,
		fontWeight = FontWeight.Medium,
		fontSize = 14.sp,
		lineHeight = 20.sp,
		letterSpacing = 0.4.sp
	),

	labelSmall = TextStyle(
		fontFamily = FontFamily.Default,
		fontWeight = FontWeight.Normal,
		fontSize = 11.sp,
		lineHeight = 16.sp,
		letterSpacing = 0.5.sp
	)
)