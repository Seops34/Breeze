package com.seop.breeze.demo.ui.picker

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import kotlin.math.sqrt

class CirclePath(
    val progress: Float,
    val startPos: Offset = Offset.Zero
): Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        // 기존 방법
//        val center = Offset(
//            x = size.center.x - ((size.center.x - startPos.x) * (1f - progress)),
//            y = size.center.y - ((size.center.y - startPos.y) * (1f - progress))
//        )

        // 신규 방법
        val center = Offset(size.center.x, size.center.y)

        val radius = sqrt(size.height * size.height + size.width * size.width) * .5f * progress

        return Outline.Generic(
            Path().apply {
                addOval(Rect(center, radius))
            }
        )
    }
}

// 확인 코드
// val center = Offset(x = startPos.x, y = startPos.y)
// val radius = 500f

// 확인 코드 - 중앙에 위치한 이미지만 정상 동작
// val center = Offset(x = startPos.x, y = startPos.y)
// val radius = sqrt(size.height * size.height + size.width * size.width) * .5f * progress

// 확인 코드 - 정상 동작 but 기존과는 다른 형태의 정상 동작
// val center = Offset(x = startPos.x, y = startPos.y)
// val radius = sqrt(size.height * size.height + size.width * size.width) * progress
// return Outline.Generic(
//     Path().apply {
//         addOval(Rect(center, radius))
//     }
// )

// 확인 코드 - center의 변경을 확인할 수 있는 코드
// val center = Offset(
//     x = size.center.x - ((size.center.x - startPos.x) * (1f - progress)),
//     y = size.center.y - ((size.center.y - startPos.y) * (1f - progress))
// )
// val radius = sqrt(size.height * size.height + size.width * size.width) * .5f * progress
// val pRadius = if (radius < 1000f) radius else 1000f
// return Outline.Generic(
//     Path().apply {
//         addOval(Rect(center, pRadius))
//     }
// )