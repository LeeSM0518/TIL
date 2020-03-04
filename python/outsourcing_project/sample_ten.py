# sample_ten.py

import os
import sys
import wx

try:
    from wx.lib.pdfviewer import pdfViewer, pdfButtonPanel
    havePyPdf = True
except ImportError:
    havePyPdf = False 

# class My_Panel
# class My_Frame
# class My_App

#-------------------------------------------------------------------------------

class My_Panel(wx.Panel):
    """
    Create a panel for my frame.
    """
    def __init__(self, parent):
        wx.Panel.__init__(self, parent, -1)

        hsizer = wx.BoxSizer(wx.HORIZONTAL)
        vsizer = wx.BoxSizer(wx.VERTICAL)

        self.buttonpanel = pdfButtonPanel(self,
                                          wx.ID_ANY,
                                          wx.DefaultPosition,
                                          wx.DefaultSize,
                                          0)

        vsizer.Add(self.buttonpanel, 0,
                   wx.GROW|wx.LEFT|wx.RIGHT|wx.TOP, 5)

        self.viewer = pdfViewer(self,
                                wx.ID_ANY,
                                wx.DefaultPosition,
                                wx.DefaultSize,
                                wx.HSCROLL|wx.VSCROLL|
                                wx.SUNKEN_BORDER)

        vsizer.Add(self.viewer, 1,
                   wx.GROW|wx.LEFT|wx.RIGHT|wx.BOTTOM, 5)
        
        loadbutton = wx.Button(self,
                               wx.ID_ANY,
                               "Load PDF file",
                               wx.DefaultPosition,
                               wx.DefaultSize,
                               0)

        vsizer.Add(loadbutton, 0,
                   wx.ALIGN_CENTER|wx.ALL, 5)

        hsizer.Add(vsizer, 1,
                   wx.GROW|wx.ALL, 5)

        self.SetSizer(hsizer)
        self.SetAutoLayout(True)

        # Introduce buttonpanel and viewer to each other.
        self.buttonpanel.viewer = self.viewer
        self.viewer.buttonpanel = self.buttonpanel

        self.Bind(wx.EVT_BUTTON, self.OnLoadButton, loadbutton)

    #---------------------------------------------------------------------------
        
    def OnLoadButton(self, event):
        """
        ...
        """
        
        dlg = wx.FileDialog(self, wildcard="*.pdf")

        if dlg.ShowModal() == wx.ID_OK:
            wx.BeginBusyCursor()
            self.viewer.LoadFile(dlg.GetPath())
            wx.EndBusyCursor()

        dlg.Destroy()

#-------------------------------------------------------------------------------

class My_Frame(wx.Frame):
    """
    Create a main frame for my application.
    """
    def __init__(self):
        style = (wx.DEFAULT_FRAME_STYLE)
        wx.Frame.__init__(self, None, -1,
                          title="PDFViewer (PyMuPDF)",
                          size=(700, 500),
                          style=style)

        #------------
        
        pnl = My_Panel(self)

        #------------

        self.CenterOnScreen(wx.BOTH)

        #------------

        self.Show(True)
        
#-------------------------------------------------------------------------------
    
class My_App(wx.App):
    """
    ...
    """
    def OnInit(self):

        #------------

        self.locale = wx.Locale(wx.LANGUAGE_ENGLISH)

        #------------

        frame = My_Frame()
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

