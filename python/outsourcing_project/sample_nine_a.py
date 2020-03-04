# sample_nine_a.py

import sys
import os
import platform
import wx

# class My_Frame
# class My_App

#-------------------------------------------------------------------------------

if os.name == "posix":
    print("\nPlatform : UNIX - Linux")
elif os.name in ['nt', 'dos', 'ce']:
    print("\nPlatform : Windows")
else:
    print("\nPlatform : ", platform.system())

#-------------------------------------------------------------------------------

class My_Frame(wx.Frame):
    """
    Create a main frame for my application.
    """
    def __init__(self, parent, id, title=""):
        wx.Frame.__init__(self,
                          parent,
                          id,
                          title,
                          size=(350, 300),
                          style=wx.DEFAULT_FRAME_STYLE)

        #------------

        # Simplified init method.
        self.SetProperties()
        self.CreateMenu()
        self.CreateCtrls()
        self.BindEvents()
        self.DoLayout()

        #------------

        self.CenterOnScreen()

    #---------------------------------------------------------------------------

    def SetProperties(self):
        """
        Set the main frame properties (title, icon...).
        """

        self.SetTitle("Reading test...")


    def CreateMenu(self):
        """
        Make the frame menus.
        """

        menub = wx.MenuBar()

        fmenu = wx.Menu()
        fmenu.Append(-1, "Sho&w PDF\tCtrl+W")
        fmenu.AppendSeparator()
        fmenu.Append(wx.ID_EXIT, "E&xit\tCtrl+X")
        menub.Append(fmenu, "&File")

        self.SetMenuBar(menub)


    def CreateCtrls(self):
        """
        Make widgets for my application.
        """

        boldFont = wx.SystemSettings.GetFont(wx.SYS_DEFAULT_GUI_FONT)
        boldFont.SetWeight(wx.BOLD)
        boldFont.SetPointSize(10)

        #------------

        # First create the controls.
        self.panel = wx.Panel(self,
                              id=-1,
                              style=wx.BORDER_THEME|
                              wx.TAB_TRAVERSAL)

        self.text = wx.StaticText(self.panel,
                                  id=-1,
                                  label="Demonstrating :")
        self.text.SetFont(boldFont)

        self.info = wx.StaticText(self.panel,
                                  id=-1,
                                  label="> Read PDF file with installed default program.")
        self.info.SetForegroundColour("red")

        self.btnView = wx.Button(self.panel,
                                 id=-1,
                                 label="Sho&w PDF")
        self.btnView.SetFocus()

        self.btnClose = wx.Button(self.panel,
                                  id=wx.ID_CLOSE,
                                  label="E&xit")


    def BindEvents(self):
        """
        Bind all the events related to my application.
        """

        # Bind some menu events to an events handler.
        self.Bind(wx.EVT_MENU, self.OnBtnViewPdf, id=-1)
        self.Bind(wx.EVT_MENU, self.OnBtnClose, id=wx.ID_EXIT)

        # Bind the close event to an event handler.
        self.Bind(wx.EVT_CLOSE, self.OnCloseWindow)

        # Bind some buttons events to an events handler.
        self.Bind(wx.EVT_BUTTON, self.OnBtnViewPdf, self.btnView)
        self.Bind(wx.EVT_BUTTON, self.OnBtnClose, self.btnClose)


    def DoLayout(self):
        """
        Manage widgets Layout.
        """

        # MainSizer is the top-level one that manages everything.
        mainSizer = wx.BoxSizer(wx.VERTICAL)

        #------------

        hBox1 = wx.BoxSizer(wx.HORIZONTAL)
        hBox1.Add(self.info, 0, wx.ALL, 15)

        #------------

        hBox2 = wx.BoxSizer(wx.HORIZONTAL)
        hBox2.Add(self.btnView, 0, wx.ALL, 10)
        hBox2.Add(self.btnClose, 0, wx.ALL, 10)

        #------------

        mainSizer.Add(self.text, 0, wx.ALL, 10)
        mainSizer.Add(wx.StaticLine(self.panel),
                      0, wx.EXPAND|wx.TOP|wx.BOTTOM, 5)
        mainSizer.Add(hBox1, 0, wx.ALL, 5)
        mainSizer.Add(hBox2, 0, wx.ALL, 5)

        #------------

        # Finally, tell the panel to use the mainSizer for layout.
        self.panel.SetSizer(mainSizer)


    def OnBtnViewPdf(self, event):
        """
        Launch a PDF program to read the file.
        """

        wx.BeginBusyCursor()
        os.startfile("wxpython.pdf")
        wx.CallAfter(wx.EndBusyCursor)


    def OnBtnClose(self, event):
        """
        ...
        """

        self.Close(True)


    def OnCloseWindow(self, event):
        """
        ...
        """

        self.Destroy()

#-------------------------------------------------------------------------------

class My_App(wx.App):
    """
    ...
    """
    def OnInit(self):

        #------------

        frame = My_Frame(None, id=-1)
        self.SetTopWindow(frame)
        frame.Show(True)

        return True

#-------------------------------------------------------------------------------

def main():
    app = My_App(False)
    app.MainLoop()

#-------------------------------------------------------------------------------

if __name__ == "__main__" :
    main()
