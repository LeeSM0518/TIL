# sample_eight.py

import os
import sys
import wx
from wx.html import HtmlEasyPrinting
from wx.html import HtmlWindow

# class MyHtmlPrinter
# class MyFrame
# class MyApp

app_dir = os.path.split(os.path.abspath(sys.argv[0]))[0]
html_file = os.path.join(app_dir, "template.html")

#-------------------------------------------------------------------------------

if os.name == "posix":
    print("\nPlatform : UNIX - Linux")
elif os.name in ['nt', 'dos', 'ce']:
    print("\nPlatform : Windows")
else:
    print("\nPlatform : ", os.system())

#-------------------------------------------------------------------------------

class MyHtmlPrinter(HtmlEasyPrinting):
    """
    ...
    """
    def __init__(self, parent):

        # Get the window name.
        name = "Person"

        # Init the HtmlEasyPrinting.
        HtmlEasyPrinting.__init__(self, name, parent)

        # Get the current script directory.
        self.current_dir = os.path.normpath(os.path.dirname(__file__))

        # Set some default printer and page options.
        self.GetPrintData().SetPaperId(wx.PAPER_LETTER)  # wx.PAPER_A4
        self.GetPrintData().SetOrientation(wx.LANDSCAPE)  # wx.PORTRAIT
        # Black and white printing if False.
        self.GetPrintData().SetColour(True)
        self.GetPageSetupData().SetMarginTopLeft((20, 20))
        self.GetPageSetupData().SetMarginBottomRight((20, 20))

    #---------------------------------------------------------------------------

    def page_setup(self):
        """
        Show page setup.
        """

        self.PageSetup()


    def print_text(self, text):
        """
        Print the text.
        """

        return self.PrintText(text, basepath=self.current_dir)


    def preview_text(self, text):
        """
        Preview html text.
        """

        # @DATE @ is replaced by the current date in default format.
        # @PAGENUM@ is replaced by page number.
        # @PAGESCNT@ is replaced by total number of pages.
        # @TIME @ is replaced by the current time in default format.
        # @TITLE@ is replaced with the title of the document.

        header = "Person :"
        footer = "Page @PAGENUM@ of @PAGESCNT@"

        self.SetHeader(header)
        self.SetFooter(footer)

        return self.PreviewText(text, basepath=self.current_dir)


    def print_file(self, file):
        """
        Print the text.
        """

        return self.PrintFile(file)


    def preview_file(self, file):
        """
        Preview html file.
        """

        return self.PreviewFile(file)

#-------------------------------------------------------------------------------

class MyFrame(wx.Frame):
    """
    Create a main frame for my application.
    """
    def __init__ (self, parent, id, title=""):
        wx.Frame.__init__(self,
                          parent,
                          id,
                          title,
                          size=(600, 417),
                          style=wx.DEFAULT_FRAME_STYLE)

        #------------

        # Sample html.
        self.sample_html = None

        #------------

        # Simplified init method.
        self.SetProperties()
        self.CreateMenu()
        self.CreateCtrls()
        self.CreatePrinter()
        self.BindEvents()
        self.DoLayout()

        #------------

        self.CenterOnScreen()

    #---------------------------------------------------------------------------

    def SetProperties(self):
        """
        Set the main frame properties (title, icon...).
        """

        self.SetTitle("Html easy printing...")


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
        Make widgets for my application.
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

        self.demo = wx.StaticText(self.panel,
                                  id=-1,
                                  label="Demonstrating :")
        self.demo.SetFont(font)

        self.stFirstname = wx.StaticText(self.panel,
                                  id=-1,
                                  label="Firstname : ")

        self.tcFirstname = wx.TextCtrl(self.panel,
                                       id=-1,
                                       size=(200, -1),
                                       value="John")

        self.stSurname = wx.StaticText(self.panel,
                                  id=-1,
                                  label="Surname : ")

        self.tcSurname = wx.TextCtrl(self.panel,
                                     id=-1,
                                     size=(200, -1),
                                     value="Doe")

        self.stAge = wx.StaticText(self.panel,
                                  id=-1,
                                  label="Age : ")

        self.tcAge = wx.TextCtrl(self.panel,
                                       id=-1,
                                       size=(200, -1),
                                       value="32")

        self.info = wx.StaticText(self.panel,
                                  id=-1,
                                  label="1) Direct printing,\n"
                                        "2) HtmlEasyPrinting class,\n"
                                        "3) Html file loading,\n"
                                        "4) Preview,\n"
                                        "5) Menu,\n"
                                        "6) Page setup.")
        self.info.SetForegroundColour("red")
        font.SetWeight(wx.NORMAL)
        self.info.SetFont(font)

        self.btnSetup = wx.Button(self.panel,
                                  id=wx.ID_PAGE_SETUP,
                                  label="Page set&up")

        self.btnPreview = wx.Button(self.panel,
                                    wx.ID_PREVIEW,
                                    label="Pre&view text")
        self.btnPreview.SetFocus()

        self.btnPrint = wx.Button(self.panel,
                                  id=wx.ID_PRINT,
                                  label="&Print")

        self.btnClose = wx.Button(self.panel,
                                  id=wx.ID_CLOSE,
                                  label="E&xit")


    def CreatePrinter(self):
        """
        Create the printer.
        """

        self.printer = MyHtmlPrinter(self)


    def LoadTemplate(self, event):
        """
        Load html template.
        """


        sSurname = self.tcSurname.GetValue()
        sFirstname = self.tcFirstname.GetValue()
        sAge = self.tcAge.GetValue()

        #------------

        # Thanks to Pascal Faut.
        # Returns html file ("template.html").
        with open(html_file, "r") as MyTemplate:
            strTemplate = MyTemplate.read()
            self.sample_html = strTemplate

            self.sample_html = self.sample_html.replace("{{SURNAME}}", sSurname)
            self.sample_html = self.sample_html.replace("{{FIRSTNAME}}", sFirstname)
            self.sample_html = self.sample_html.replace("{{AGE}}", sAge)


    def BindEvents(self):
        """
        Bind all the events related to my application.
        """

        # Bind some menu events to an events handler.
        self.Bind(wx.EVT_MENU, self.OnBtnPageSetup, id=wx.ID_PAGE_SETUP)
        self.Bind(wx.EVT_MENU, self.OnBtnPreview, id=wx.ID_PREVIEW)
        self.Bind(wx.EVT_MENU, self.OnBtnPrint, id=wx.ID_PRINT)
        self.Bind(wx.EVT_MENU, self.OnBtnClose, id=wx.ID_EXIT)

        # Bind some buttons events to an events handler.
        self.Bind(wx.EVT_BUTTON, self.OnBtnPageSetup, self.btnSetup)
        self.Bind(wx.EVT_BUTTON, self.OnBtnPreview, self.btnPreview)
        self.Bind(wx.EVT_BUTTON, self.OnBtnPrint, self.btnPrint)
        self.Bind(wx.EVT_BUTTON, self.OnBtnClose, self.btnClose)

        # Bind the close event to an event handler.
        self.Bind(wx.EVT_CLOSE, self.OnCloseWindow)


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

        # wx.GridBagSizer(vgap=0, hgap=0).
        gSizer = wx.GridBagSizer(2, 2)
        gSizer.Add(self.stFirstname, (0, 0), span=(wx.DefaultSpan),
                   flag=wx.ALIGN_CENTER_VERTICAL|wx.ALIGN_RIGHT)
        gSizer.Add(self.tcFirstname, (0, 1), span=wx.DefaultSpan)

        gSizer.Add(self.stSurname, (1, 0), span=wx.DefaultSpan,
                   flag=wx.ALIGN_CENTER_VERTICAL|wx.ALIGN_RIGHT)
        gSizer.Add(self.tcSurname, (1, 1), span=wx.DefaultSpan)

        gSizer.Add(self.stAge, (2, 0), span=wx.DefaultSpan,
                   flag=wx.ALIGN_CENTER_VERTICAL|wx.ALIGN_RIGHT)
        gSizer.Add(self.tcAge, (2, 1), span=wx.DefaultSpan)

        #------------

        mainSizer.Add(self.demo, 0, wx.ALL, 10)
        mainSizer.Add(wx.StaticLine(self.panel),
                      0, wx.EXPAND|wx.TOP, 5)
        mainSizer.Add(gSizer, 0, wx.ALL, 20)
        mainSizer.Add(hBox1, 0, wx.LEFT|wx.BOTTOM, 5)
        mainSizer.Add(hBox2, 0, wx.ALL, 5)

        #------------

        # Finally, tell the panel to use the mainSizer for layout.
        self.panel.SetSizer(mainSizer)


    def OnBtnPageSetup(self, event):
        """
        Page setup click.
        """

        # Page setup dialog.
        self.printer.page_setup()


    def OnBtnPreview(self, event):
        """
        Print preview click.
        """

        # Load template.
        self.LoadTemplate(event)

        if self.sample_html is not None:
            # Preview html text.
            print("Preview result:", self.printer.preview_text(self.sample_html))


    def OnBtnPrint(self, event):
        """
        Print click.
        """

        # Load template.
        self.LoadTemplate(event)

        if self.sample_html is not None:
            # Print html text.
            print("Print result:", self.printer.print_text(self.sample_html))


    def OnBtnClose(self, event):
        """
        Close application.
        """

        self.Close(True)


    def OnCloseWindow(self, event):
        """
        Destroy application.
        """

        self.Destroy()

#-------------------------------------------------------------------------------

class MyApp(wx.App):
    """
    wx.App sub-class that is the example application.
    """
    def OnInit(self):
        """
        Init MyApp instance.
        """

        #------------

        self.locale = wx.Locale(wx.LANGUAGE_ENGLISH)

        #------------

        frame = MyFrame(None, id=-1)
        self.SetTopWindow(frame)
        frame.Show(True)

        return True

#-------------------------------------------------------------------------------

def main():
    app = MyApp(False)
    app.MainLoop()

#-------------------------------------------------------------------------------

if __name__ == "__main__" :
    main()
