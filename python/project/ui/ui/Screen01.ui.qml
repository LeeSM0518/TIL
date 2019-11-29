import QtQuick 2.12
import ui 1.0
import QtQuick.Controls 2.3
import QtQuick.Layouts 1.3
import QtQuick.Studio.Effects 1.0
import QtQuick.Studio.Components 1.0
import Qt.SafeRenderer 1.1

Rectangle {
    id: rectangle
    width: 640
    height: Constants.height
    clip: true

    Image {
        id: noteImage
        width: 45
        height: 42
        anchors.left: parent.left
        anchors.leftMargin: 25
        anchors.top: parent.top
        anchors.topMargin: 20
        fillMode: Image.PreserveAspectFit
        source: "note.png"
    }

    Button {
        id: createButton
        width: 64
        height: 34
        text: qsTr("추가")
        anchors.left: parent.left
        anchors.leftMargin: 76
        anchors.top: parent.top
        anchors.topMargin: 28
    }

    Button {
        id: removeButton
        width: 64
        height: 34
        text: qsTr("삭제")
        anchors.left: parent.left
        anchors.leftMargin: 152
        anchors.top: parent.top
        anchors.topMargin: 28
    }

    Image {
        id: alarmImage
        x: 265
        width: 46
        height: 40
        anchors.right: parent.right
        anchors.rightMargin: 329
        anchors.top: parent.top
        anchors.topMargin: 22
        fillMode: Image.PreserveAspectFit
        source: "alarm.png"
    }

    Button {
        id: startButton
        x: 317
        width: 64
        height: 34
        text: qsTr("시작")
        anchors.right: parent.right
        anchors.rightMargin: 259
        anchors.top: parent.top
        anchors.topMargin: 28
    }

    Button {
        id: stopButton
        x: 392
        width: 64
        height: 34
        text: qsTr("정지")
        anchors.right: parent.right
        anchors.rightMargin: 184
        anchors.top: parent.top
        anchors.topMargin: 28
    }

    ColumnLayout {
        id: columnLayout
        x: 25
        y: 96
        width: 593
        height: 602

        RowLayout {
            id: rowLayout
            width: 100
            height: 100

            CheckBox {
                id: checkBox
                text: qsTr("Check Box")
            }
            Text {
                id: element
                text: qsTr("Text")
                font.pixelSize: 16
            }

            Button {
                id: button
                text: qsTr("Button")
            }


        }

        RowLayout {
            id: rowLayout1
            width: 100
            height: 100
        }

        RowLayout {
            id: rowLayout2
            width: 100
            height: 100
        }

        RowLayout {
            id: rowLayout3
            width: 100
            height: 100
        }

        RowLayout {
            id: rowLayout4
            width: 100
            height: 100
        }
    }




}

/*##^##
Designer {
    D{i:1;anchors_x:35;anchors_y:43}D{i:2;anchors_x:118;anchors_y:57}D{i:3;anchors_height:34;anchors_x:202;anchors_y:57}
D{i:4;anchors_x:382;anchors_y:43}D{i:5;anchors_height:34;anchors_x:465;anchors_y:57}
D{i:6;anchors_y:57}
}
##^##*/

