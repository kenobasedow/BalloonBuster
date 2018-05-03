package de.basedow.keno.ballonbuster

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.MathUtils

class Balloon() : BaseActor() {

    val speed = 80f * MathUtils.random(0.5f, 2f)
    val amplitude = 50f * MathUtils.random(0.5f, 2f)
    val oscillation = 0.01f * MathUtils.random(0.5f, 2f)
    val initialY = 120f * MathUtils.random(0.5f, 2f)
    var time = 0f
    var offsetX = -100f

    init {
        texture = Texture("red-balloon.png")
        x = offsetX
    }

    override fun act(delta: Float) {
        super.act(delta)
        time += delta
        val posX = speed * time + offsetX
        val posY = amplitude * MathUtils.sin(oscillation * posX) + initialY
        setPosition(posX, posY)
    }
}