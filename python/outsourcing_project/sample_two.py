# sample_two.py

import sys
import os
import platform
import wx

# class My_Printout
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

class My_Printout(wx.Printout):
    """
    Create a printout.
    """
    def __init__(self, text, title):
        wx.Printout.__init__(self, title)

        #------------

        self.lines = text

    #---------------------------------------------------------------------------

    def OnBeginDocument(self, start, end):
        """
        ...
        """

        return super(My_Printout, self).OnBeginDocument(start, end)


    def OnEndDocument(self):
        """
        ...
        """

        super(My_Printout, self).OnEndDocument()


    def OnBeginPrinting(self):
        """
        ...
        """

        super(My_Printout, self).OnBeginPrinting()


    def OnEndPrinting(self):
        """
        ...
        """

        super(My_Printout, self).OnEndPrinting()


    def OnPreparePrinting(self):
        """
        ...
        """

        super(My_Printout, self).OnPreparePrinting()


    def HasPage(self, page):
        """
        ...
        """

        if page <= 2:
            return True
        else:
            return False


    def GetPageInfo(self):
        """
        ...
        """

        return (1, 2, 1, 2)


    def OnPrintPage(self, page):
        """
        ...
        """

        dc = self.GetDC()

        # (wx.MM_METRIC) ---> Each logical unit is 1 mm.
        # (wx.MM_POINTS) ---> Each logical unit is a "printer point" i.e.
        dc.SetMapMode(wx.MM_POINTS)

        dc.SetTextForeground("red")
        dc.SetFont(wx.Font(20, wx.SWISS, wx.NORMAL, wx.BOLD))
        dc.DrawText(self.lines, 50, 100)

        # R, V, B.
        dc.SetPen(wx.Pen(wx.Colour(255, 20, 5)))
        dc.SetBrush(wx.Brush(wx.Colour(30, 255, 20)))
        # x, y, radius.
        dc.DrawCircle(100, 275, 25)
        # x, y, width, height.
        dc.DrawEllipse(100, 275, 75, 50)

        return True

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
                          size=(600, 350),
                          style=wx.DEFAULT_FRAME_STYLE)

        #------------

        # Simplified init method.
        self.SetProperties()
        self.CreateMenu()
        self.CreateCtrls()
        self.CreatePrintData()
        self.BindEvents()
        self.DoLayout()

        #------------

        self.CenterOnScreen()

    #---------------------------------------------------------------------------

    def SetProperties(self):
        """
        Set the main frame properties (title, icon...).
        """

        self.SetTitle("Printing test...")


    def CreateMenu(self):
        """
        Make the frame menus.
        """

        menub = wx.MenuBar()

        fmenu = wx.Menu()
        fmenu.Append(wx.ID_PAGE_SETUP, "Page set&up\tCtrl+U")
        fmenu.Append(wx.ID_PREVIEW, "Print pre&view\tCtrl+V")
        fmenu.Append(wx.ID_PRINT, "&Print\tCtrl+P")
        fmenu.AppendSeparator()
        fmenu.Append(wx.ID_EXIT, "E&xit\tCtrl+X")
        menub.Append(fmenu, "&File")

        self.SetMenuBar(menub)


    def CreateCtrls(self):
        """
        Make widgets for my app.
        """

        font = wx.SystemSettings.GetFont(wx.SYS_DEFAULT_GUI_FONT)
        font.SetWeight(wx.BOLD)
        font.SetPointSize(10)

        #------------

        # First create the controls.
        self.panel = wx.Panel(self,
                              id=-1,
                              style=wx.BORDER_THEME|
                              wx.TAB_TRAVERSAL)

        self.text = wx.StaticText(self.panel,
                                  id=-1,
                                  label="Demonstrating :")
        self.text.SetFont(font)

        self.info = wx.StaticText(self.panel,
                                  id=-1,
                                  label="1) Direct printing,\n"
                                        "2) Printout class,\n"
                                        "3) Preview,\n"
                                        "4) Menu,\n"
                                        "5) Page setup.")
        self.info.SetForegroundColour("red")
        font.SetWeight(wx.NORMAL)
        self.info.SetFont(font)

        self.tc = wx.TextCtrl(self.panel,
                              id=-1,
                              size=(200, -1),
                              value="Hello, World ! A sample text.")

        self.btnSetup = wx.Button(self.panel,
                                  id=wx.ID_PAGE_SETUP,
                                  label="Page set&up")

        self.btnPreview = wx.Button(self.panel,
                                    id=wx.ID_PREVIEW,
                                    label="Print pre&view")
        self.btnPreview.SetFocus()

        self.btnPrint = wx.Button(self.panel,
                                  id=wx.ID_PRINT,
                                  label="&Print")

        self.btnClose = wx.Button(self.panel,
                                  id=wx.ID_CLOSE,
                                  label="E&xit")


    def CreatePrintData(self):
        """
        Create printing data.
        """

        self.printdata = wx.PrintData()

        self.printdata.SetPrinterName('')
        self.printdata.SetOrientation(wx.PORTRAIT)
        self.printdata.SetPaperId(wx.PAPER_A4)
        self.printdata.SetQuality(wx.PRINT_QUALITY_DRAFT)
        # Black and white printing if False.
        self.printdata.SetColour(True)
        self.printdata.SetNoCopies(1)
        self.printdata.SetCollate(True)
        # self.printData.SetPrintMode(wx.PRINT_MODE_PRINTER)


    def BindEvents(self):
        """
        Bind all the events related to my application.
        """

        # Bind some menu events to an events handler.
        self.Bind(wx.EVT_MENU, self.OnBtnPageSetup, id=wx.ID_PAGE_SETUP)
        self.Bind(wx.EVT_MENU, self.OnBtnPreview, id=wx.ID_PREVIEW)
        self.Bind(wx.EVT_MENU, self.OnBtnPrint, id=wx.ID_PRINT)
        self.Bind(wx.EVT_MENU, self.OnBtnClose, id=wx.ID_EXIT)

        # Bind the close event to an event handler.
        self.Bind(wx.EVT_CLOSE, self.OnCloseWindow)

        # Bind some buttons events to an events handler.
        self.Bind(wx.EVT_BUTTON, self.OnBtnPageSetup, self.btnSetup)
        self.Bind(wx.EVT_BUTTON, self.OnBtnPreview, self.btnPreview)
        self.Bind(wx.EVT_BUTTON, self.OnBtnPrint, self.btnPrint)
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
        hBox2.Add(self.btnSetup, 0, wx.ALL, 10)
        hBox2.Add(self.btnPreview, 0, wx.ALL, 10)
        hBox2.Add(self.btnPrint, 0, wx.ALL, 10)
        hBox2.Add(self.btnClose, 0, wx.ALL, 10)

        #------------

        mainSizer.Add(self.text, 0, wx.ALL, 10)
        mainSizer.Add(wx.StaticLine(self.panel),
                      0, wx.EXPAND|wx.TOP|wx.BOTTOM, 5)
        mainSizer.Add(self.tc, 0, wx.ALL, 15)
        mainSizer.Add(hBox1, 0, wx.ALL, 5)
        mainSizer.Add(hBox2, 0, wx.ALL, 5)

        #------------

        # Finally, tell the panel to use the mainSizer for layout.
        self.panel.SetSizer(mainSizer)


    def OnBtnPageSetup(self, event):
        """
        Show the PrinterSetup dialog.
        """

        psdd = wx.PageSetupDialogData(self.printdata)

        psdd.EnablePrinter(True)
        # psdd.CalculatePaperSizeFromId()

        #------------

        dlg = wx.PageSetupDialog(self, psdd)
        dlg.ShowModal()

        #------------

        # This makes a copy of the wx.PrintData instead of just saving
        # a reference to the one inside the PrintDialogData that will
        # be destroyed when the dialog is destroyed
        self.printdata = wx.PrintData(dlg.GetPageSetupData().GetPrintData())

        dlg.Destroy()


    def OnBtnPreview(self, event):
        """
        Show the print preview.
        """

        text = self.tc.GetValue()

        #------------

        data = wx.PrintDialogData(self.printdata)

        printout1 = My_Printout(text, "- My printing object")
        printout2 = My_Printout(text, "- My printing object")

        printPreview = wx.PrintPreview(printout1, printout2, data)

        # Initial zoom value.
        if "__WXMAC__" in wx.PlatformInfo:
            printPreview.SetZoom(50)
        else:
            printPreview.SetZoom(35)

        if not printPreview.IsOk():
            wx.MessageBox(("There was a problem printing.\nPerhaps "\
                           "your current printer is \nnot "\
                           "set correctly ?"),
                          ("Printing"),
                          wx.OK)
            return

        else:
            previewFrame = wx.PreviewFrame(printPreview, None, "Print preview")
            previewFrame.Initialize()
            previewFrame.SetPosition(self.GetPosition())
            previewFrame.SetSize(self.GetSize())
            # Or full screen :
            # previewFrame.Maximize()
            previewFrame.Show(True)
            previewFrame.Layout()


    def OnBtnPrint(self, event):
        """
        Prints the document.
        """

        text = self.tc.GetValue()

        #------------

        pdd = wx.PrintDialogData(self.printdata)
        pdd.SetPrintData(self.printdata)
        pdd.SetMinPage(1)
        pdd.SetMaxPage(1)
        pdd.SetFromPage(1)
        pdd.SetToPage(1)
        pdd.SetPrintToFile(False)
        # pdd.SetSetupDialog(False)
        # pdd.EnableSelection(True)
        # pdd.EnablePrintToFile(True)
        # pdd.EnablePageNumbers(True)
        # pdd.SetAllPages(True)

        #------------

        printer = wx.Printer(pdd)

        myPrintout = My_Printout(text, "- My printing object")

        if not printer.Print(self, myPrintout, True):
            wx.MessageBox(("There was a problem printing.\nPerhaps "\
                           "your current printer is \nnot "\
                           "set correctly ?"),
                          ("Printing"),
                          wx.OK)
            return

        else:
            self.printData = wx.PrintData(printer.GetPrintDialogData().GetPrintData())
        myPrintout.Destroy()


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

        self.locale = wx.Locale(wx.LANGUAGE_ENGLISH)

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
