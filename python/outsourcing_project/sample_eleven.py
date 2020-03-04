# sample_eleven.py

"""

Read PDF files (.pdf) with wxPython 
using wx.lib.pdfwin.PDFWindow class ActiveX control 
from wxPython's new wx.activex module, this allows one  
to use an ActiveX control, as if it would be wx.Window. 
it embeds the Adobe Acrobat Reader.
This works only with Windows.

"""

import os
import sys
import wx

if wx.Platform == "__WXMSW__":
    from wx.lib.pdfwin import PDFWindow
else:
    dlg = wx.MessageDialog(self,
                           message="This demo only works on Microsoft Windows.",
                           caption="Sorry",
                           style=wx.OK|
                                 wx.ICON_WARNING)
    dlg.ShowModal()
    dlg.Destroy()

appDir = os.path.split(os.path.abspath(sys.argv[0]))[0]
doc = os.path.join(appDir, "document.pdf")

# class My_Frame
# class My_App

#-------------------------------------------------------------------------------

class My_Frame(wx.Frame):
    def __init__(self, parent, id, title):
        wx.Frame.__init__(self, parent, -1,
                          title, size=(720, 600),
                          style=wx.DEFAULT_FRAME_STYLE |
                                wx.NO_FULL_REPAINT_ON_RESIZE)
   
        #------------

        # Simplified init method.
        self.CreateCtrls()
        self.LoadPdf()
        self.BindEvents()
        self.DoLayout()
        
        #---------------------------------------------------------------
        
        self.CenterOnScreen()
        
    #-------------------------------------------------------------------
        
    def CreateCtrls(self):
        """
        ...
        """
        
        fontSize = self.GetFont().GetPointSize()
        boldFont = wx.Font(fontSize, wx.SWISS, wx.NORMAL, wx.BOLD)

        #------------
        
        self.panel = wx.Panel(self, -1)

        #------------

        # Add PDFWindow        
        self.pdf = PDFWindow(self.panel, style=wx.SUNKEN_BORDER)
        self.pdf.setView("FitV")
        self.pdf.setPageMode("Bookmarks")
        self.pdf.setLayoutMode("SinglePage")
        self.pdf.setShowScrollbars(True)
        
        #------------
        
        # Add controls
        self.btn1 = wx.Button(self.panel, -1, "&Open")
        self.btn1.SetToolTip("Open PDF file.")

        self.btn2 = wx.Button(self.panel,  -1, "<<", size=(30, -1))
        self.btn2.SetToolTip("First page.")
        self.btn2.SetFont(boldFont)
        
        self.btn3 = wx.Button(self.panel, -1, "<", size=(30, -1))
        self.btn3.SetToolTip("Previous page.") 
        self.btn3.SetFont(boldFont)

        self.btn4 = wx.Button(self.panel, -1, ">", size=(30, -1))
        self.btn4.SetToolTip("Next page.") 
        self.btn4.SetFont(boldFont)

        self.btn5 = wx.Button(self.panel,  -1, ">>", size=(30, -1))
        self.btn5.SetToolTip("Last page.")
        self.btn5.SetFont(boldFont)

        self.txt1 = wx.StaticText(self.panel, -1, "   Go to page" )
        self.txtCtrl = wx.TextCtrl(self.panel, -1, "0", size=[30, -1])
        
        self.txt2 = wx.StaticText(self.panel, -1, "     Zoom")
        self.zoom = wx.Choice(self.panel, -1,
                              choices=["Default",
                                       "Fit",
                                       "FitH",
                                       "FitV",
                                       "25%",
                                       "50%",
                                       "75%",
                                       "100%",
                                       "125%",
                                       "200%",
                                       "400%"])
        self.zoom.SetSelection(0)
        self.zoom.SetToolTip("Zoom.")
            
        self.btn6 = wx.Button(self.panel, -1, "&Pr&int")
        self.btn6.SetToolTip("Print PDF file.")
            
        self.btn7 = wx.Button(self.panel, -1, "&Quit")
        self.btn7.SetToolTip("Quit application.")


    def BindEvents(self):
        """
        Bind all the events related to my frame.
        """

        self.Bind(wx.EVT_BUTTON, self.OnOpenBtn, self.btn1)
        self.Bind(wx.EVT_BUTTON, self.OnFirstPageBtn, self.btn2)
        self.Bind(wx.EVT_BUTTON, self.OnPrevPageBtn, self.btn3)
        self.Bind(wx.EVT_BUTTON, self.OnNextPageBtn, self.btn4)
        self.Bind(wx.EVT_BUTTON, self.OnLastPageBtn, self.btn5)
        self.Bind(wx.EVT_BUTTON, self.OnPrintBtn, self.btn6)
        self.Bind(wx.EVT_BUTTON, self.OnCloseBtn, self.btn7)

        self.Bind(wx.EVT_TEXT, self.OnGotoPage, self.txtCtrl)
        self.Bind(wx.EVT_CHOICE, self.OnZoom, self.zoom)

        self.Bind(wx.EVT_CLOSE, self.OnCloseWindow)
        self.Bind(wx.EVT_ERASE_BACKGROUND, self.OnEraseBackground)

        
    def DoLayout(self):
        """
        Manage widgets Layout.
        """

        # MainSizer is the top-level one that manages everything.        
        sizer = wx.BoxSizer(wx.VERTICAL)
        sizer.Add(self.pdf, proportion=1, flag=wx.EXPAND)

        #------------
        
        btnSizer = wx.BoxSizer(wx.HORIZONTAL)
        btnSizer.Add(self.btn1, proportion=0, flag=wx.ALIGN_CENTER|wx.ALL, border=5)
        btnSizer.Add(self.btn2, proportion=0, flag=wx.ALIGN_CENTER|wx.ALL, border=2)
        btnSizer.Add(self.btn3, proportion=0, flag=wx.ALIGN_CENTER|wx.ALL, border=2)
        btnSizer.Add(self.btn4, proportion=0, flag=wx.ALIGN_CENTER|wx.ALL, border=2)
        btnSizer.Add(self.btn5, proportion=0, flag=wx.ALIGN_CENTER|wx.ALL, border=2)
        btnSizer.Add(self.txt1, proportion=0, flag=wx.ALIGN_CENTER|wx.ALL, border=5)
        btnSizer.Add(self.txtCtrl, proportion=0, flag=wx.ALIGN_CENTER|wx.ALL, border=5)
        btnSizer.Add(self.txt2, proportion=0, flag=wx.ALIGN_CENTER|wx.ALL, border=5)
        btnSizer.Add(self.zoom, proportion=0, flag=wx.ALIGN_CENTER|wx.ALL, border=5)
        btnSizer.Add(self.btn6, proportion=0, flag=wx.ALIGN_CENTER|wx.ALL, border=5)
        btnSizer.Add(self.btn7, proportion=0, flag=wx.ALIGN_CENTER|wx.ALL, border=5)
        btnSizer.Add((50,-1), proportion=2, flag=wx.EXPAND)

        #------------
        
        sizer.Add(btnSizer, proportion=0, flag=wx.EXPAND)

        #------------
        
        # Finally, tell the panel to use the mainSizer for layout.      
        self.panel.SetSizer(sizer)
        self.panel.SetAutoLayout(True)
        

    def LoadPdf(self):
        """
        ...
        """
        
        self.pdf.LoadFile(doc)

      
    def OnOpenBtn(self, event):
        """
        ...
        """
        
        dlg = wx.FileDialog(self, wildcard="*.pdf")

        if dlg.ShowModal() == wx.ID_OK:
            wx.BeginBusyCursor()
            self.pdf.LoadFile(dlg.GetPath())
            wx.EndBusyCursor()

        dlg.Destroy()

            
    def OnFirstPageBtn(self, event):
        """
        ...
        """
        
        self.pdf.gotoFirstPage()

 
    def OnPrevPageBtn(self, event):
        """
        ...
        """
        
        self.pdf.gotoPreviousPage()


    def OnNextPageBtn(self, event):
        """
        ...
        """
        
        self.pdf.gotoNextPage()


    def OnLastPageBtn(self, event):
        """
        ...
        """
        
        self.pdf.gotoLastPage()


    def OnGotoPage(self, event):
        """
        ...
        """
        
        npage = event.GetEventObject().GetValue()

        try:
            self.pdf.setCurrentPage(int(npage))
        except ValueError:
            pass

        
    def OnZoom(self, event):
        """
        ...
        """
        
        astring = event.GetEventObject().GetStringSelection()

        if astring.startswith('Fit'):
            self.pdf.setView(astring)
        else:
            try:
                percent = float(astring.replace('%',''))
                self.pdf.setZoom(percent)
            except ValueError:
                pass

                
    def OnPrintBtn(self, event):
        """
        ...
        """
        
        self.pdf.Print()

        
    def OnCloseBtn(self, event):
        """
        ...
        """
        
        self.Close(True)

            
    def OnEraseBackground(self, event):
        """
        ...
        """
        
        dc = event.GetDC()

        if not dc:
            dc = wx.ClientDC(self)
            rect = self.GetUpdateRegion().GetBox()
            dc.SetClippingRect(rect)


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

        frame = My_Frame(None, -1, "PDFWindow (ActiveX)")
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

