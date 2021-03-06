package com.joshjcarrier.minecontrol;

import com.joshjcarrier.minecontrol.framework.profiles.GamePadProfileList;
import com.joshjcarrier.minecontrol.ui.controllers.MainController;
import com.joshjcarrier.minecontrol.ui.views.MainView;
import com.joshjcarrier.persistence.IStorage;
import com.joshjcarrier.persistence.IniStorage;
import com.joshjcarrier.rxgamepad.RxGamePadList;

import javax.swing.*;

/**
 * Application entry point.
 * @author joshjcarrier
 *
 */
public class App
{
	public App()
	{
		// TODO sync with minecontrol runtime
		// controller reader service async
        IStorage storage = new IniStorage();
        storage.load();
        RxGamePadList rxGamePadList = new RxGamePadList();
        GamePadProfileList gamePadProfileList = new GamePadProfileList(storage);
        MainController mainController = new MainController(rxGamePadList, gamePadProfileList);
		
		MainView view = mainController.index();
		view.setVisible(true);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			// Get the native look and feel class name
			String nativeLF = UIManager.getSystemLookAndFeelClassName();

			// Install the native look and feel, allowing startup to proceed with default look and feel if native not possible
			try {
			    UIManager.setLookAndFeel(nativeLF);
			} catch (InstantiationException e) {
			} catch (ClassNotFoundException e) {
			} catch (UnsupportedLookAndFeelException e) {
			} catch (IllegalAccessException e) {
			}
			
			new App();
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex + "\r\n" + ex.getStackTrace()[0] + "\r\nContact josh@joshjcarrier.com .", AppInfo.ProductName + " failure", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
			System.exit(1);
		}
	}
}
