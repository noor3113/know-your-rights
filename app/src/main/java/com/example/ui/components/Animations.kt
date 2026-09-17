package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ui.theme.DarkBorder
import com.example.ui.theme.OrangePrimary
import kotlinx.coroutines.delay

/**
 * Reusable press animation modifier that slightly scales down to ~0.95x
 * when pressed and smoothly springs back when released.
 */
@Composable
fun Modifier.bounceClick(
    scaleDown: Float = 0.95f,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onClick: (() -> Unit)? = null
): Modifier {
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) scaleDown else 1.0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "bounceScale"
    )

    val baseModifier = this.graphicsLayer {
        scaleX = scale
        scaleY = scale
    }

    return if (onClick != null) {
        baseModifier.clickable(
            interactionSource = interactionSource,
            indication = null,
            onClick = onClick
        )
    } else {
        baseModifier
    }
}

/**
 * Category Card press animation with gentle scale down and an orange glow border effect on press.
 */
@Composable
fun Modifier.glowPressCard(
    scaleDown: Float = 0.96f,
    glowColor: Color = OrangePrimary,
    cornerRadius: Dp = 16.dp,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onClick: () -> Unit
): Modifier {
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) scaleDown else 1.0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "glowScale"
    )

    val glowAlpha by animateFloatAsState(
        targetValue = if (isPressed) 0.9f else 0.0f,
        animationSpec = tween(durationMillis = 150),
        label = "glowAlpha"
    )

    val borderColor = if (glowAlpha > 0.05f) {
        glowColor.copy(alpha = glowAlpha)
    } else {
        DarkBorder
    }

    val borderWidth = if (glowAlpha > 0.05f) 2.dp else 1.dp

    return this
        .graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
        .border(
            width = borderWidth,
            color = borderColor,
            shape = RoundedCornerShape(cornerRadius)
        )
        .clickable(
            interactionSource = interactionSource,
            indication = null,
            onClick = onClick
        )
}

/**
 * Fade-in + slide-up entrance animation for cards when a screen first loads.
 */
@Composable
fun AnimatedCardEntrance(
    index: Int = 0,
    delayPerIndex: Long = 70L,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(index * delayPerIndex)
        isVisible = true
    }

    AnimatedVisibility(
        visible = isVisible,
        modifier = modifier,
        enter = fadeIn(
            animationSpec = tween(durationMillis = 320, easing = FastOutSlowInEasing)
        ) + slideInVertically(
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioLowBouncy,
                stiffness = Spring.StiffnessMediumLow
            ),
            initialOffsetY = { 50 }
        ),
        exit = fadeOut(animationSpec = tween(durationMillis = 150))
    ) {
        content()
    }
}
