import QtQuick 2.0
import HelperWidgets 2.0
import QtQuick.Layouts 1.0

Column {
    anchors.left: parent.left
    anchors.right: parent.right

    Section {
        anchors.left: parent.left
        anchors.right: parent.right
        caption: qsTr("Blend")

        SectionLayout {
            rows: 2
            Label {
                text: qsTr("Mode")
                toolTip: qsTr("This property defines the mode which is used when foregroundSource is blended over source.")
            }
            SecondColumnLayout {
                ComboBox {
                    id: blendMode

                    backendValue: backendValues.mode
                    Layout.fillWidth: true

                    useInteger: true
                    manualMapping: true

                    model: ["normal", "addition", "average", "color", "colorBurn", "colorDodge", "darken", "darkerColor", "difference", "divide", "exclusion", "hardLight", "hue", "lighten", "lighterColor", "lightness", "multiply", "negation", "saturation", "screen", "subtract", "softLight"]

                    property bool block: false

                    onValueFromBackendChanged: blendMode.fromBackendToFrontend()

                    onCurrentTextChanged: {
                        if (!__isCompleted)
                            return

                        if (block)
                            return

                        backendValues.mode.value = blendMode.model[blendMode.currentIndex]
                    }

                    Connections {
                        target: modelNodeBackend
                        onSelectionChanged: blendMode.fromBackendToFrontend()
                    }

                    function fromBackendToFrontend()
                    {
                        if (!__isCompleted)
                            return

                        block = true

                        currentIndex = blendMode.model.indexOf(backendValues.mode.value)

                        block = false
                    }
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
