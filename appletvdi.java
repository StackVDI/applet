/**
 * Applet para direccionar conexiones RDP a máquinas virtuales en cloud
 * Este applet tiene que estar incluido en un paǵina web que le
 * pase como paramétros el nombre de usuario de la máquina remota,
 * el passowrd del usuario y la ip de la máquina remota (ver ejemplo adjunto)
 * @param login 
 * @param password
 * @param vdesktop_ip
 * @version 0.8 16/05/2015
 * @author Cayetano Reinaldos Duarte
 * @todo ver README adjunto
 **/
import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;

public class appletvdi extends Applet {

  String osName = System.getProperty("os.name");
  String osVersion = System.getProperty("os.version");

  public void init() { 
    Process process;
    int response;

    String vdidesktop_ip, login, password;
    String absolutePath = "";

    vdidesktop_ip = getParameter("vdidesktop_ip");
    login = getParameter("login");
    password = getParameter("password");

    if (this.osName.equals("Linux")) {
      try {
        process = Runtime.getRuntime().exec("/usr/bin/xfreerdp --ignore-certificate --disable-wallpaper -u " + login + " -p " + password + " -f " + vdidesktop_ip); 
      }	       
      catch (IOException e) {
        e.printStackTrace();
      }
    } 
    else if(this.osName.contains("OS X")) {
      //estamos en un cliente MAC
      try {
        process = Runtime.getRuntime().exec("/Applications/CoRD.app/Contents/MacOS/CoRD -u " + login + " -p " + password + " -f  -host " + vdidesktop_ip); 
      }
      catch(IOException e) {
        e.printStackTrace();
      }
    }
    else {
      //estamos en un cliente Windows
      try {
        process = Runtime.getRuntime().exec("cmdkey /generic:" + vdidesktop_ip + " /user:" + login + " /pass:" + password);
        process = Runtime.getRuntime().exec("mstsc /v:" + vdidesktop_ip);
      }

      catch(IOException e) {
        e.printStackTrace();
      }
    }
  }
}
