import QtQuick 2.0
import HelperWidgets 2.0
import QtQuick.Layouts 1.0


Column {
    anchors.left: parent.left
    anchors.right: parent.right

    Section {
        anchors.left: parent.left
        anchors.right: parent.right
        caption: qsTr("Hue and Saturation")

        SectionLayout {
            rows: 2
            Label {
                text: qsTr("Hue")
                toolTip: qsTr("This property defines the hue value which is added to the source hue value.")
            }
            SecondColumnLayout {
                SpinBox {
                    backendValue: backendValues.adjustHue
                    Layout.preferredWidth: 80
                    decimals: 2
                    minimumValue: -1
                    maximumValue: 1
                    stepSize: 0.1
                }
                ExpandingSpacer {
                }
            }

            Label {
                text: qsTr("Lightness")
                toolTip: qsTr("This property defines the lightness value which is added to the source saturation value.")
            }
            SecondColumnLayout {
                SpinBox {
                    backendValue: backendValues.adjustLightness
                    Layout.preferredWidth: 80
                    decimals: 2
                    minimumValue: -1
                    maximumValue: 1
                    stepSize: 0.1
                }
                ExpandingSpacer {
                }
            }

            Label {
                text: qsTr("Saturation")
                toolTip: qsTr("This property defines the saturation value value which is added to the source saturation value.")
            }
            SecondColumnLayout {
                SpinBox {
                    backendValue: backendValues.adjustSaturation
                    Layout.preferredWidth: 80
                    decimals: 2
                    minimumValue: -1
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
        caption: qsTr("Caching")

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
            }
        }
    }
}
