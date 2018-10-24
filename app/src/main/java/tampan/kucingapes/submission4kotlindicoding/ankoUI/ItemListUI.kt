/*
 * ItemListUI.kt on Submission4kotlindicoding
 * Developed by Muhammad Utsman on 10/24/18 6:30 AM
 * Last modified 10/24/18 6:29 AM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission4kotlindicoding.ankoUI

import android.os.Build
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import tampan.kucingapes.submission4kotlindicoding.R

class ItemListUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            cardView {
                lparams {
                    width = matchParent
                    height = wrapContent
                    topMargin = dip(6)
                    bottomMargin = dip(6)
                    rightMargin = dip(12)
                    leftMargin = dip(12)
                }

                radius = 8f

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    elevation = 3f
                }

                linearLayout {
                    lparams {
                        width = matchParent
                        height = wrapContent
                    }
                    val typedValue = TypedValue()
                    context.theme.resolveAttribute(android.R.attr.selectableItemBackground, typedValue, true)
                    backgroundResource = typedValue.resourceId

                    orientation = LinearLayout.VERTICAL
                    padding = dip(6)

                    textView {
                        id = R.id.date_match
                        textColorResource = R.color.colorPrimary
                    }.lparams {
                        width = wrapContent
                        height = wrapContent
                        bottomMargin = dip(6)
                        gravity = Gravity.CENTER

                    }

                    linearLayout {
                        orientation = LinearLayout.HORIZONTAL
                        gravity = Gravity.CENTER

                        imageView {
                            id = R.id.badge_home
                        }.lparams {
                            width = dip(30)
                            height = dip(30)
                        }

                        textView {
                            id = R.id.home_team
                            gravity = Gravity.END
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                                textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                            }
                        }.lparams {
                            width = dip(0)
                            height = wrapContent
                            weight = 3f
                        }

                        textView {
                            id = R.id.home_score
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                            }
                        }.lparams(width = dip(0), height = wrapContent) {
                            weight = 1f
                        }

                        textView(context.getString(R.string.versus)) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                            }
                        }.lparams {
                            width = dip(0)
                            height = wrapContent
                            weight = 0.5f
                        }

                        textView {
                            id = R.id.away_score
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                            }
                        }.lparams {
                            width = dip(0)
                            height = wrapContent
                            weight = 1f
                        }

                        textView {
                            id = R.id.away_team
                        }.lparams {
                            width = dip(0)
                            height = wrapContent
                            weight = 3f
                        }

                        imageView {
                            id = R.id.badge_away
                        }.lparams {
                            width = dip(30)
                            height = dip(30)
                        }
                    }
                }

            }
        }
    }
}