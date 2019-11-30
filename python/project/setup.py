from setuptools import setup

setup(name='Scheduler',
      description='py2app test application',
      version='0.0.1',
      setup_requires=['py2app'],
      app=['Scheduler.py'],
      options={
          'py2app': {
              'includes': ['PyQt5.QtWidgets',
                           'PyQt5.QtGui']
          }
      }

)