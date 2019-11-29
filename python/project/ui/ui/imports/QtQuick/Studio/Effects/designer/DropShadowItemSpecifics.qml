import QtQuick 2.0
import HelperWidgets 2.0
import QtQuick.Layouts 1.0


Column {
    anchors.left: parent.left
    anchors.right: parent.right

    Section {
        anchors.left: parent.left
        anchors.right: parent.right
        caption: qsTr("Drop Shadow Color")

        ColorEditor {
            caption: qsTr("Drop Shadow Color")
            backendValue: backendValues.color
            supportGradient: false
        }
    }

    Section {
        anchors.left: parent.left
        anchors.right: parent.right
        caption: qsTr("Drop Shadow")

        SectionLayout {
            rows: 2
            Label {
                text: qsTr("Radius")
                toolTip: qsTr("Radius defines the softness of the shadow. A larger radius causes the edges of the shadow to appear more blurry.")
            }
            SecondColumnLayout {
                SpinBox {
                    backendValue: backendValues.radius
                    Layout.preferredWidth: 80
                    decimals: 1
                    minimumValue: 0
                    maximumValue: 100
                    stepSize: 1
                }
                ExpandingSpacer {
                }
            }

            Label {
                text: qsTr("Samples")
                toolTip: qsTr("This property defines how many samples are taken per pixel when edge softening blur calculation is done, ideally, this value should be twice as large as the highest required radius value plus one.")
            }
            SecondColumnLayout {
                SpinBox {
                    backendValue: backendValues.samples
                    Layout.preferredWidth: 80
                    decimals: 0
                    minimumValue: 0
                    maximumValue: 201
                    stepSize: 1
                }
                ExpandingSpacer {
                }
            }

            Label {
                text: qsTr("Spread")
                toolTip: qsTr("This property defines how large part of the shadow color is strengthened near the source edges.")
            }
            SecondColumnLayout {
                SpinBox {
                    backendValue: backendValues.spread
                    Layout.preferredWidth: 80
                    decimals: 2
                    minimumValue: 0
                    maximumValue: 1
                    stepSize: 0.1
                }
                ExpandingSpacer {
                }
            }
        }
    }

    Section {
        anchors.left: parent.left
        anchors.right: parent.right
        caption: qsTr("Offsets")

        SectionLayout {
            rows: 2
            Label {
                text: qsTr("Horizontal offset")
                toolTip: qsTr("HorizontalOffset defines the offset for the rendered shadow compared to the DropShadow items horizontal position.")
            }
            SecondColumnLayout {
                SpinBox {
                    backendValue: backendValues.horizontalOffset
                    Layout.preferredWidth: 80
                    decimals: 1
                    minimumValue: -1000
                    maximumValue: 1000
                    stepSize: 1
                }
                ExpandingSpacer {
                }
            }

            Label {
                text: qsTr("Vertical offset")
                toolTip: qsTr("VerticalOffset defines the offset for the rendered shadow compared to the DropShadow items vertical position. ")
            }
            SecondColumnLayout {
                SpinBox {
                    backendValue: backendValues.verticalOffset
                    Layout.preferredWidth: 80
                    decimals: 1
                    minimumValue: -1000
                    maximumValue: 1000
                    stepSize: 1
                }
                ExpandingSpacer {
                }
            }
        }
    }

    Section {
        anchors.left: parent.left
        anchors.right: parent.right
        caption: qsTr("Caching and Border")

        SectionLayout {
            rows: 2
            Label {
                text: qsTr("Cached")
                toolTip: qsTr("This property allows the effect output pixels to be cached in order to improve the rendering performance.")
            }
            SecondColumnLayout {
                CheckBox {
                    Layout.fillWidth: true
                    backendValue: backendValues.cached
                    text: backendValues.cached.valueToString
                }
                ExpandingSpacer {
                }
            }
            Label {
                text: qsTr("Transparent border")
                toolTip: qsTr("When set to true, the exterior of the item is padded with a transparent edge, making sampling outside the source texture use transparency instead of the edge pixels.")
            }
            SecondColumnLayout {
                CheckBox {
                    Layout.fillWidth: true
                    backendValue: backendValues.transparentBorder
                    text: backendValues.transparentBorder.valueToString
                }
            }
        }
    }
}
