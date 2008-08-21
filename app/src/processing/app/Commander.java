/* -*- mode: java; c-basic-offset: 2; indent-tabs-mode: nil -*- */

/*
  Part of the Processing project - http://processing.org

  Copyright (c) 2004-08 Ben Fry and Casey Reas
  Copyright (c) 2001-04 Massachusetts Institute of Technology

  This program is free software; you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation; either version 2 of the License, or
  (at your option) any later version.

  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.

  You should have received a copy of the GNU General Public License
  along with this program; if not, write to the Free Software Foundation,
  Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package processing.app;


import java.io.IOException;
import java.io.PrintStream;


public class Commander {
  static final String helpArg = "--help";
  static final String buildArg = "--build";
  static final String preprocArg = "--preprocess";
  static final String sketchArg = "--sketch=";
  static final String outputArg = "--output=";
  static final String exportAppletArg = "--export-applet";
  static final String exportApplicationArg = "--export-application";

  static final int HELP = -1;
  static final int PREPROCESS = 0;
  static final int BUILD = 1;
  static final int EXPORT_APPLET = 2;
  static final int EXPORT_APPLICATION = 3;


  public Commander(String[] args) {
    String sketchPath = null;
    String outputPath = null;
    int mode = HELP;

    for (String arg : args) {
      if (arg.equals(helpArg)) {
        // mode already set to HELP

      } else if (arg.equals(buildArg)) {
        mode = BUILD;

      } else if (arg.equals(preprocArg)) {
        mode = PREPROCESS;

      } else if (arg.equals(exportAppletArg)) {
        mode = EXPORT_APPLET;

      } else if (arg.equals(exportApplicationArg)) {
        mode = EXPORT_APPLICATION;

      } else if (arg.startsWith(sketchArg)) {
        sketchPath = arg.substring(sketchArg.length());

      } else if (arg.startsWith(outputArg)) {
        outputPath = arg.substring(outputArg.length());
      }
    }

    if (mode == HELP) {
      printCommandLine(System.out);
      System.exit(0);

    } else if (sketchPath == null) {
      complainAndQuit("No sketch path specified.");
      
    } else if (outputPath == null) {
      complainAndQuit("No output path specified.");

    } else if (outputPath.equals(sketchPath)) {
      complainAndQuit("The sketch path and output path cannot be identical.");
      
    } else if (!sketchPath.toLowerCase().endsWith(".pde")) {
      complainAndQuit("Sketch path must point to the main .pde file.");
      
    } else if (mode == PREPROCESS) {
      try {
        Sketch sketch = new Sketch(null, sketchPath);
        
      } catch (IOException e) {
        e.printStackTrace();
        System.exit(1);
      }
    } else if (mode == BUILD) {
      
    } else if (mode == EXPORT_APPLET) {
      
    } else if (mode == EXPORT_APPLICATION) {
      
    }
  }


  static void complainAndQuit(String lastWords) {
    printCommandLine(System.err);
    System.err.println(lastWords);
    System.exit(1);
  }


  static void printCommandLine(PrintStream out) {
    out.println("Processing rocks the console.");
    out.println();
//  out.println("./processing )
  }
}