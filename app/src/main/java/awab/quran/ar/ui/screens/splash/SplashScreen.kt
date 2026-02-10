package awab.quran.ar.ui.screens.splash

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import awab.quran.ar.ui.theme.QuranGreen
import awab.quran.ar.ui.theme.QuranGreenLight
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1500,
            easing = FastOutSlowInEasing
        ),
        label = "alpha"
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(2500)
        
        // التحقق من تسجيل الدخول
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            onNavigateToHome()
        } else {
            onNavigateToLogin()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        QuranGreen,
                        QuranGreenLight
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.alpha(alphaAnim.value)
        ) {
            // أيقونة التطبيق
            Text(
                text = "📖",
                fontSize = 100.sp,
                modifier = Modifier.padding(top = 0.dp, bottom = 24.dp)
            )

            // اسم التطبيق
            Text(
                text = "نديم",
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(top = 0.dp, bottom = 8.dp)
            )

            // الوصف
            Text(
                text = "رفيقك في حفظ القرآن الكريم",
                fontSize = 18.sp,
                color = Color.White.copy(alpha = 0.9f),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 32.dp, vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // آية قرآنية
            Text(
                text = "وَرَتِّلِ الْقُرْآنَ تَرْتِيلًا",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White.copy(alpha = 0.95f),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 32.dp)
            )

            Text(
                text = "سورة المزمل - آية 4",
                fontSize = 14.sp,
                color = Color.White.copy(alpha = 0.8f),
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        // نص في الأسفل
        Text(
            text = "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ",
            fontSize = 16.sp,
            color = Color.White.copy(alpha = 0.7f),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(top = 0.dp, bottom = 32.dp)
                .alpha(alphaAnim.value)
        )
    }
}
