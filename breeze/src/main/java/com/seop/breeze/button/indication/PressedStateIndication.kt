package com.seop.breeze.button.indication

//object PressedStateIndication: Indication {
//    private class DefaultDebugIndicationInstance(
//        private val isPressed: State<Boolean>
//    ): IndicationInstance {
//        override fun ContentDrawScope.drawIndication() {
//            drawContent()
//            if (isPressed.value) {
//                drawRect(Color.Black.copy(alpha = 0.2f), size = size)
//            }
//        }
//    }
//    @Composable
//    override fun rememberUpdatedInstance(interactionSource: InteractionSource): IndicationInstance {
//        val isPressed = interactionSource.collectIsPressedAsState()
//        return remember { DefaultDebugIndicationInstance(isPressed) }
//    }
//}