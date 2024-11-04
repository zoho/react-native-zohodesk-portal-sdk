package com.zohodeskportalconfiguration

import android.graphics.Typeface

class RNZDPFont private constructor(
    var regular: Typeface? = null,
    var light: Typeface? = null,
    var medium: Typeface? = null,
    var thin: Typeface? = null,
    var bold: Typeface? = null,
    var black: Typeface? = null,
    var semibold: Typeface? = null,
    var heavy: Typeface? = null,
    var ultralight: Typeface? = null,
    var unrecognised: Typeface? = null
) {

    data class Builder(
        private var regular: Typeface? = null,
        private var light: Typeface? = null,
        private var medium: Typeface? = null,
        private var thin: Typeface? = null,
        private var bold: Typeface? = null,
        private var black: Typeface? = null,
        private var semibold: Typeface? = null,
        private var heavy: Typeface? = null,
        private var ultralight: Typeface? = null,
        private var unrecognised: Typeface? = null
    ) {

        fun setRegular(regular: Typeface?) = apply {
            this.regular = regular
        }

        fun setLight(light: Typeface?) = apply {
            this.light = light
        }

        fun setMedium(medium: Typeface?) = apply {
            this.medium = medium
        }

        fun setThin(thin: Typeface?) = apply {
            this.thin = thin
        }

        fun setBold(bold: Typeface?) = apply {
            this.bold = bold
        }

        fun setBlack(black: Typeface?) = apply {
            this.black = black
        }

        fun setSemiBold(semibold: Typeface?) = apply {
            this.semibold = semibold
        }

        fun setHeavy(heavy: Typeface?) = apply {
            this.heavy = heavy
        }

        fun setUltraLight(ultralight: Typeface?) = apply {
            this.ultralight = ultralight
        }

        fun setUnRecognised(unrecognised: Typeface?) = apply {
            this.unrecognised = unrecognised
        }

        fun build(): RNZDPFont {
            return RNZDPFont(
                regular, light, medium, thin, bold, black, semibold, heavy, ultralight, unrecognised
            )
        }
    }
}
