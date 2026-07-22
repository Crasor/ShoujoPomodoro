package com.shoujopomodoro.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.shoujopomodoro.ui.theme.ShoujoPink
import com.shoujopomodoro.ui.theme.ShoujoLavender
import com.shoujopomodoro.ui.theme.ShoujoGold
import com.shoujopomodoro.ui.theme.ShoujoSkyBlue
import com.shoujopomodoro.ui.theme.ShoujoMint
import com.shoujopomodoro.ui.theme.ShoujoRose
import com.shoujopomodoro.domain.model.CharacterState
import kotlinx.coroutines.delay

@Composable
fun EnhancedShoujoCharacter(
    characterState: CharacterState,
    size: Dp = 220.dp,
    modifier: Modifier = Modifier
) {
    val eyeBlink = remember { mutableStateOf(0f) }
    val breathScale = remember { mutableStateOf(1f) }
    val headTilt = remember { mutableStateOf(0f) }
    
    // Eye blink animation
    LaunchedEffect(characterState) {
        while (true) {
            delay(3000 + (Math.random() * 2000).toLong())
            eyeBlink.value = 1f
            delay(100)
            eyeBlink.value = 0f
        }
    }
    
    // Breath animation
    LaunchedEffect(characterState) {
        while (true) {
            delay(1500)
            breathScale.value = 1.02f
            delay(500)
            breathScale.value = 1f
        }
    }
    
    // Head tilt animation for different states
    LaunchedEffect(characterState) {
        when (characterState) {
            CharacterState.IDLE -> {
                while (true) {
                    headTilt.value = (Math.sin(System.currentTimeMillis().toDouble() / 1000) * 2).toFloat()
                    delay(500)
                }
            }
            CharacterState.FOCUSING -> {
                headTilt.value = -3f
            }
            CharacterState.RESTING -> {
                headTilt.value = 5f
            }
            CharacterState.ALERTING -> {
                headTilt.value = 0f
                // Add shake effect
                while (true) {
                    headTilt.value = (Math.sin(System.currentTimeMillis().toDouble() / 200) * 10).toFloat()
                    delay(50)
                }
            }
        }
    }

    Box(
        modifier = modifier
            .size(size)
            .then(if (characterState == CharacterState.ALERTING) Modifier else Modifier),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val centerX = size.toPx() / 2
            val centerY = size.toPx() / 2
            
            // Background glow
            drawCircle(
                color = Color(0x33FF69B4),
                radius = size.toPx() * 0.6f,
                center = Offset(centerX, centerY)
            )
            
            // Face
            drawCircle(
                color = Color(0xFFFFE0E0),
                radius = size.toPx() * 0.4f,
                center = Offset(centerX, centerY)
            )
            
            // Hair (pink with gradient)
            val hairPath = Path().apply {
                moveTo(centerX - size.toPx() * 0.3f, centerY - size.toPx() * 0.2f)
                quadraticTo(
                    centerX, centerY - size.toPx() * 0.5f,
                    centerX + size.toPx() * 0.3f, centerY - size.toPx() * 0.2f
                )
                lineTo(centerX + size.toPx() * 0.25f, centerY + size.toPx() * 0.1f)
                lineTo(centerX - size.toPx() * 0.25f, centerY + size.toPx() * 0.1f)
                close()
            }
            drawPath(
                path = hairPath,
                color = ShoujoPink.copy(alpha = 0.8f)
            )
            
            // Eyes
            val eyeSize = size.toPx() * 0.08f
            val eyeOffsetY = size.toPx() * 0.1f
            
            // Left eye
            drawCircle(
                color = Color.Black,
                radius = eyeSize,
                center = Offset(centerX - size.toPx() * 0.15f, centerY - eyeOffsetY)
            )
            // Right eye
            drawCircle(
                color = Color.Black,
                radius = eyeSize,
                center = Offset(centerX + size.toPx() * 0.15f, centerY - eyeOffsetY)
            )
            
            // Pupils (smaller)
            val pupilSize = eyeSize * 0.5f
            drawCircle(
                color = Color.White,
                radius = pupilSize,
                center = Offset(centerX - size.toPx() * 0.15f + eyeSize * 0.2f, centerY - eyeOffsetY - eyeSize * 0.2f)
            )
            drawCircle(
                color = Color.White,
                radius = pupilSize,
                center = Offset(centerX + size.toPx() * 0.15f - eyeSize * 0.2f, centerY - eyeOffsetY - eyeSize * 0.2f)
            )
            
            // Blink effect
            if (eyeBlink.value > 0) {
                drawRect(
                    color = Color.White,
                    topLeft = Offset(centerX - size.toPx() * 0.2f, centerY - eyeOffsetY - eyeSize * 0.1f),
                    size = androidx.compose.ui.geometry.Size(size.toPx() * 0.4f, eyeSize * eyeBlink.value * 0.2f)
                )
            }
            
            // Mouth
            val mouthY = centerY + size.toPx() * 0.15f
            when (characterState) {
                CharacterState.IDLE -> {
                    drawArc(
                        color = Color.Black,
                        startAngle = 180f,
                        sweepAngle = 180f,
                        useCenter = false,
                        topLeft = Offset(centerX - size.toPx() * 0.1f, mouthY),
                        size = androidx.compose.ui.geometry.Size(size.toPx() * 0.2f, size.toPx() * 0.05f)
                    )
                }
                CharacterState.FOCUSING -> {
                    // Determined straight line
                    drawLine(
                        color = Color.Black,
                        start = Offset(centerX - size.toPx() * 0.1f, mouthY),
                        end = Offset(centerX + size.toPx() * 0.1f, mouthY),
                        strokeWidth = 2f
                    )
                }
                CharacterState.RESTING -> {
                    // Happy smile
                    drawArc(
                        color = Color.Black,
                        startAngle = 180f,
                        sweepAngle = 180f,
                        useCenter = false,
                        topLeft = Offset(centerX - size.toPx() * 0.15f, mouthY - size.toPx() * 0.05f),
                        size = androidx.compose.ui.geometry.Size(size.toPx() * 0.3f, size.toPx() * 0.1f)
                    )
                }
                CharacterState.ALERTING -> {
                    // Surprised O mouth
                    drawCircle(
                        color = Color.Black,
                        radius = size.toPx() * 0.06f,
                        center = Offset(centerX, mouthY)
                    )
                }
            }
            
            // Cheek blush
            val blushSize = size.toPx() * 0.08f
            drawCircle(
                color = ShoujoRose.copy(alpha = 0.6f),
                radius = blushSize,
                center = Offset(centerX - size.toPx() * 0.25f, centerY + size.toPx() * 0.05f)
            )
            drawCircle(
                color = ShoujoRose.copy(alpha = 0.6f),
                radius = blushSize,
                center = Offset(centerX + size.toPx() * 0.25f, centerY + size.toPx() * 0.05f)
            )
            
            // Accessories based on state
            when (characterState) {
                CharacterState.FOCUSING -> {
                    // Sweat drop
                    drawCircle(
                        color = Color.White,
                        radius = size.toPx() * 0.04f,
                        center = Offset(centerX + size.toPx() * 0.25f, centerY - size.toPx() * 0.25f)
                    )
                    drawCircle(
                        color = Color.Gray,
                        radius = size.toPx() * 0.015f,
                        center = Offset(centerX + size.toPx() * 0.25f, centerY - size.toPx() * 0.25f)
                    )
                }
                CharacterState.RESTING -> {
                    // Zzz bubbles
                    val zzzSize = size.toPx() * 0.06f
                    drawCircle(
                        color = ShoujoSkyBlue.copy(alpha = 0.4f),
                        radius = zzzSize,
                        center = Offset(centerX + size.toPx() * 0.4f, centerY - size.toPx() * 0.1f)
                    )
                    drawCircle(
                        color = ShoujoSkyBlue.copy(alpha = 0.4f),
                        radius = zzzSize * 0.7f,
                        center = Offset(centerX + size.toPx() * 0.5f, centerY - size.toPx() * 0.15f)
                    )
                    drawCircle(
                        color = ShoujoSkyBlue.copy(alpha = 0.4f),
                        radius = zzzSize * 0.5f,
                        center = Offset(centerX + size.toPx() * 0.6f, centerY - size.toPx() * 0.2f)
                    )
                }
                CharacterState.ALERTING -> {
                    // Exclamation marks
                    val exSize = size.toPx() * 0.08f
                    drawRect(
                        color = Color.Red,
                        topLeft = Offset(centerX + size.toPx() * 0.1f, centerY - size.toPx() * 0.3f),
                        size = androidx.compose.ui.geometry.Size(exSize, exSize * 2f)
                    )
                    drawRect(
                        color = Color.Red,
                        topLeft = Offset(centerX - size.toPx() * 0.1f, centerY - size.toPx() * 0.3f),
                        size = androidx.compose.ui.geometry.Size(exSize, exSize * 2f)
                    )
                }
                else -> {}
            }
        }
    }
}
