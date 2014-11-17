package ApplicationLogic;
/**
 * 
 */

/**
 * @author Sebastian Römer
 * @author Christoph Otto
 */
public class HTMLSites
{
	static HTMLSites instance = null;

	static final String HTML_END = "\r\n    </body>\r\n</html>";
	static String 		html_board = "";
	static StringBuffer html = new StringBuffer();
	static StringBuffer taString = new StringBuffer();
	String [] p;
	/**
	 * 
	 */
	public HTMLSites()
	{
	}
	
	
	public static HTMLSites getInstance()
	{
		if(instance == null)
		{
			instance = new HTMLSites();
		}
		
		return instance;
	}
	
	
	/**
	 * 
	 */
	public String getIndexHtml(String addon)
	{
		html.setLength(0);
		html.append("<div id=\"main\" style=\"width: 500px; margin-left: auto; margin-right: auto; text-align: center;\">");
		html.append("<div id=\"logo\"></div><div id=\"container\">");
		html.append("<p style=\"color:#000000\"><b>Spielername eingeben</b></p>");
		html.append("<form action=\"form_action.asp\"><input autofocus type=\"text\" placeholder=\"Name\" maxlength=\"9\"name=\"nickname\" value=\"\" /><br/><input type=\"submit\" value=\"Lobby betreten\" /></form>");
		
		if(addon != null)
		{
			html.append("<br><div id=\"error\"><b>"+addon+"</b></div>");
		}
		
		html.append("</br></br></br><div id=\"manualStart\">"+getManual()+"</div></div>");
		
		return html.toString();
	}
	
	
	/**
	 * 
	 */
	public String getHtmlStart(int refresh, String addon)
	{
		html.setLength(0);
		html.append("<!doctype html>");
		html.append("<html>\r\n");
		html.append("    <title>Ludo-Brettspiel</title>\r\n");
		html.append("    <link rel=\"stylesheet\" href=\"style.css\" type=\"text/css\" media=\"screen\" />\r\n");
		html.append("    <meta http-equiv=\"refresh\" content=\"" + refresh + addon + "\" />\r\n\r\n");
		html.append("    <body>\r\n");
		html.append("    ");
		
		return html.toString();
	}
	
	
	/**
	 * 
	 */
	public String getHtmlEnd()
	{
		return HTML_END;
	}
	
	public String createLudoBoard(String name, int dice, String playerColor, boolean isCurrentPlayer, String addon){
		divManager.getInstance().createBoard();
		p = HTTPServer.getInstance().getPlayerArray(Manager.getInstance().getPlayers());
		String diceImg = "";
		if(dice == 0){
			diceImg = "dice0.png";
		}
		if(dice == 1){
			diceImg = "dice1.png";
		}
		else if(dice == 2){
			diceImg = "dice2.png";
		}
		else if(dice == 3){
			diceImg = "dice3.png";
		}
		else if(dice == 4){
			diceImg = "dice4.png";
		}
		else if(dice == 5){
			diceImg = "dice5.png";
		}
		else if(dice == 6){
			diceImg = "dice6.png";
		}
		String color = "FFFFFF";
		String sdice;
		if (dice==0){
			sdice = " ";
		}
		else{
			sdice =  Integer.toString(dice);
		}
		
		if (playerColor.equals("green")){
			color = "00FF00";
		}
		else if (playerColor.equals("yellow")){
			color = "FFFF00";
		}
		else if (playerColor.equals("blue")){
			color = "0000FF";
		}
		else if (playerColor.equals("red")){
			color = "FF0000";
		}
		
		html.setLength(0);
		html.append("<div id=\"board_main\">");
		html.append("<div id=\"wrapper\">");
		html.append("<div id=\"left_pane\">");
		html.append("<div id=\"manual\"><a href=\"manual.html\" target=\"_blank\"><img src=\"help.png\" width=\"30\" height=\"30\"></a></div>");
		//html.append("<p style=\"color:#000000\"><b><u>Ludo-Game</u></b></p>");
		
		if(isCurrentPlayer)
		{
			html.append("<div id=\"dice\">");
			html.append("<form action=\"random\">");
			html.append("<input type=\"image\" src=\""+diceImg+"\" border=\"0\">");
			html.append("</form>");
			html.append("</div>");
		}
		else
		{
			html.append("<div id=\"dice\">");
			html.append("<p><img src=\"dice0.png\" alt=\"W&uumlrfel\"></p>");
			html.append("</div>");
		}
		html.append("<p style=\"color:#000000\"><div id=\"color\"<p style=\"color:#"+color+"\">"+name+"</p></div>");
		//html.append("<p style=\"color:#000000\"><b>Wurf: "+sdice+"<br></b></p>");
		
		if(!addon.isEmpty())
		{
			html.append("<p style=\"color:#000000\">" + addon + "</p>");
		}
		else
		{
			html.append("<p style=\"color:#000000\"><br></p>");
		}
		
		//html.append("<p style=\"color:#000000\">Log:</p>");
		html.append("<textarea cols=\"27\" rows=\"25\" style=\"margin-top:40px; margin-left:12px\" disabledfont-family:Courier New,Courier; font-size:8pt; color:#0000C0 >"+taString.toString()+"</textarea>");
		html.append("</div>");
		html.append("<div id=\"right_pane\">");
		html.append("<div id=\"centerFieldRed\"><b>"+Manager.getInstance().getCenterField("red")+"</b></div>");
		html.append("<div id=\"centerFieldGreen\"><b>"+Manager.getInstance().getCenterField("green")+"</b></div>");
		html.append("<div id=\"centerFieldYellow\"><b>"+Manager.getInstance().getCenterField("yellow")+"</b></div>");
		html.append("<div id=\"centerFieldBlue\"><b>"+Manager.getInstance().getCenterField("blue")+"</b></div>");
		html.append("<div id=\"name_red\"><b>"+p[3]+"</b></div>");
		html.append("<div id=\"name_green\"><b>"+p[0]+"</b></div>");
		html.append("<div id=\"name_yellow\"><b>"+p[1]+"</b></div>");
		html.append("<div id=\"name_blue\"><b>"+p[2]+"</b></div>");
		
		for(div d:divManager.getInstance().divList){
			html.append(d.toString());
		}
		html.append("</div>");
		return html.toString();
		
	}
	
	
	public String createLandingPage(String player1, String player2, String player3, String player4, boolean startGame, String error)
	{	
		html.setLength(0);
		html.append("<div id=\"main\">");
		html.append("<form action=\"start_game.asp\">");
		html.append("<div id=\"one\">");
		html.append("<p style=\"color:#000000\"><b>Host</b></p>");
		html.append("<input type=\"text\" name=\"nickname1\" value=\"" + player1 + "\" readonly=\"readonly\" /><br />");
		html.append("</div>");
		html.append("<div id=\"two\">");
		html.append("<p style=\"color:#000000\"><b>Spieler 2</b></p>");
		html.append("<input type=\"text\" name=\"nickname2\" value=\"" + player2 + "\" readonly=\"readonly\" /><br />");
		if(player2.equals("")){
			html.append("<input type=\"checkbox\" name =\"bot\" value=\"player2\"/>Bot hinzufügen");
		}
		html.append("</div>");
		html.append("<div id=\"three\">");
		html.append("<p style=\"color:#000000\"><b>Spieler 3</b></p>");
		html.append("<input type=\"text\" name=\"nickname3\" value=\"" + player3 + "\" readonly=\"readonly\" /><br />");
		if(player3.equals("")){
			html.append("<input type=\"checkbox\" name =\"bot\" value=\"player3\"/>Bot hinzufügen");
		}
		html.append("</div>");
		html.append("<div id=\"four\">");
		html.append("<p style=\"color:#000000\"><b>Spieler 4</b></p>");
		html.append("<input type=\"text\" name=\"nickname4\" value=\"" + player4 + "\" readonly=\"readonly\" /><br />");
		if(player4.equals("")){
			html.append("<input type=\"checkbox\" name =\"bot\" value=\"player4\"/>Bot hinzufügen");
		}
		html.append("<br/>");
		html.append("<br/>");
		html.append("</div>");
		
		//if(startGame){
			html.append("<input type=\"submit\" value=\"Spiel starten\" />");
		//}
		html.append("</form>");
		html.append("</div>");
		
		if(error != null)
		{
			html.append("<br><div id=\"error\"><b>"+error+"</b></div>");
		}
		
		return html.toString();
	}
	
	public String endRanking(){
		html.setLength(0);

		html.append("<div id=\"main\">");
		html.append("</br></br></br><p style=\"color:#000000\"><b>Ranking: </b></p></br></br>");
		html.append("<p style=\"color:#000000\"><b>1. Platz: "+Manager.getInstance().getPodium()[0].getPlayerName()+"</p></br></br");
		html.append("<p style=\"color:#000000\"><b>2. Platz: "+Manager.getInstance().getPodium()[1].getPlayerName()+"</p></br></br");
		
		if(Manager.getInstance().getPodium()[2] != null)
		{
			html.append("<p style=\"color:#000000\"><b>3. Platz: "+Manager.getInstance().getPodium()[2].getPlayerName()+"</p></br></br");
		}
		
		html.append("</div>");
		html.append("</div>");
		
		html.append("<form action=\"restart_game.asp\">");
		html.append("<input type=\"submit\" value=\"neues Spiel starten\" />");
		html.append("</form");
		
		return html.toString();
	}
	
	public String gameFull(){
		html.setLength(0);

		html.append("<div id=\"main\">");
		html.append("</br></br><p style=\"color:#000000\"><b>Das Spiel ist leider voll.</b></p>");
		html.append("</div>");

		html.append("</div>");
		return html.toString();
	}
	
	public String gameRunning(){
		html.setLength(0);

		html.append("<div id=\"main\">");
		html.append("</br></br><p style=\"color:#000000\"><b>Es läuft bereits ein Spiel, bitte warten!</b></p>");
		html.append("</div>");

		html.append("</div>");
		return html.toString();
	}
	
	public String sameIp(){
		html.setLength(0);

		html.append("<div id=\"main\">");
		html.append("<p style=\"color:#000000\"><b>Von dieser IP wurde das Spiel bereits betreten</b></p>");
		html.append("</div>");

		html.append("</div>");
		return html.toString();
	}
	
	public static String getManual(){
		String text = "<b>Regeln:</b></br></br>" +
					"Ludo ist ein Gesellschaftsspiel für zwei bis vier Spieler. " +
					"Das Ziel des Spieles besteht darin, als erstes die vier eigenen Spielfiguren durch Würfeln von den Startfeldern " +
					"ins Zielfeld (home) zu ziehen. Dazu müssen die Figuren das Spielfeld einmal umrunden. " +
					"</br></br><b>Spielstart</b></br></br>" +
					"Jeder Spieler erhält 4 Figuren einer Farbe. 3 Figuren werden auf das gleichfarbige Viereck gesetzt und eine " +
					"auf das davor liegende Startfeld. " +
					"Um den Beginner zu ermitteln, wird reihum gewürfelt. Der Spieler, der die höchste Augenzahl würfel beginnt." +
					"</br></br><b>Spielablauf</b></br></br>" +
					"Der Spieler, der an der Reihe ist, würfelt und zieht entsprechend der Augenzahl eine seiner Spielfiguren, die auf dem Spielfeld stehen," +
					"im Uhrzeigersinn weiter. Wer eine 6 würfelt darf nochmal würfeln. " +
					"Bei einer 6 darf der Spieler (muss aber nicht) eine seiner Figuren, die noch im Start-Viereck stehen," +
					"auf das Startfeld stellen. " +
					"Es darf nur ein Spielstein auf einem Feld stehen." +
					"Trifft eine Figur auf ein bereits besetztes Feld, so wird die gegnerische Figur geschlagen und muss in ihr Viereck zurück." +
					"Sollte eine eigene Figur das Feld besetzen, so vereinigen sich die beiden Figuren zu einer einzelnen." +
					"Reihum würfelt nun jeder Spieler und zieht seine Spielfiguren entsprechend weiter." +
					"</br></br><b>Spielziel</b></br></br>" +
					"Gewinner ist der Spieler, der zuerst alle seine Figuren auf das Zielfeld gezogen hat." +
					"Dabei muss mit einem genauem Wurf auf das Zielfeld gezogen werden.";
		return text;
	}
	
	public void setTaBo() {
		int length = taString.length();
		taString.setLength(0);
		taString.append(""+Manager.getInstance().getTextArea());
		
	}
	
	/*	
	public String init(){
		divManager.getInstance().addChipToDiv("f1001", 1, "red");
		divManager.getInstance().addChipToDiv("f1002", 2, "red");
		divManager.getInstance().addChipToDiv("f1003", 3, "red");
		divManager.getInstance().addChipToDiv("f1004", 4, "red");
		divManager.getInstance().addChipToDiv("f2001", 1, "green");
		divManager.getInstance().addChipToDiv("f2002", 2, "green");
		divManager.getInstance().addChipToDiv("f2003", 3, "green");
		divManager.getInstance().addChipToDiv("f2004", 4, "green");
		divManager.getInstance().addChipToDiv("f3001", 1, "blue");
		divManager.getInstance().addChipToDiv("f3002", 2, "blue");
		divManager.getInstance().addChipToDiv("f3003", 3, "blue");
		divManager.getInstance().addChipToDiv("f3004", 4, "blue");
		divManager.getInstance().addChipToDiv("f4001", 1, "yellow");
		divManager.getInstance().addChipToDiv("f4002", 2, "yellow");
		divManager.getInstance().addChipToDiv("f4003", 3, "yellow");
		divManager.getInstance().addChipToDiv("f4004", 4, "yellow");
		for(div d:divManager.getInstance().divList){
			html.append(d.toString());
		}
		return html.toString();
	}*/
	
	/*public String moveChipToDiv(String divId, int chipId, String chipColor){
		divManager.getInstance().moveChipToDiv(divId, chipId, chipColor);
		HTMLSites.getInstance().createLudoBoard();
		for(div d:divManager.getInstance().divList){
			html.append(d.toString());
		}
		return html.toString();
	}*/
	
	
	
/*	public String init(){
		HTMLSites.getInstance().MoveRedChipTo("1", true);
		HTMLSites.getInstance().MoveRedChipTo("2", true);
		HTMLSites.getInstance().MoveRedChipTo("3", true);
		HTMLSites.getInstance().MoveRedChipTo("4", true);
		HTMLSites.getInstance().MoveGreenChipTo("1", true);
		HTMLSites.getInstance().MoveGreenChipTo("2", true);
		HTMLSites.getInstance().MoveGreenChipTo("3", true);
		HTMLSites.getInstance().MoveGreenChipTo("4", true);
		HTMLSites.getInstance().MoveBlueChipTo("1", true);
		HTMLSites.getInstance().MoveBlueChipTo("2", true);
		HTMLSites.getInstance().MoveBlueChipTo("3", true);
		HTMLSites.getInstance().MoveBlueChipTo("4", true);
		HTMLSites.getInstance().MoveYellowChipTo("1", true);
		HTMLSites.getInstance().MoveYellowChipTo("2", true);
		HTMLSites.getInstance().MoveYellowChipTo("3", true);
		HTMLSites.getInstance().MoveYellowChipTo("4", true);
		return html.toString();
	}
	
	public String createLudoBoard(String username)
	{
		html.setLength(0);
		html.append("<div id=\"board_main\">");
		html.append("<div id=\"left_pane\">");
		html.append("<p style=\"color:#000000\"><b><u>Ludo-Game</u></b></p>");
		html.append("<p style=\"color:#000000\"><b>Spieler:" + username + "</b></p>");
		html.append("<form action=\"random\">");
		html.append("<input type=\"image\" src=\"dice.gif\" border=\"0\">");
		html.append("</form>");
		html.append("<p style=\"color:#000000\"><b>letzter Wurf:</b></p>");		
		html.append("</div>");
		html.append("<div id=\"right_pane\">");
		html.append("<div id=red>");
		html.append("<div id=\"fr1\"></div>");
		html.append("<div id=\"fr2\"></div>");
		html.append("<div id=\"fr3\"></div>");
		html.append("<div id=\"fr4\"></div>");
		html.append("</div>");
		html.append("<div id=green>");
		html.append("<div id=\"fg1\"></div>");
		html.append("<div id=\"fg2\"></div>");
		html.append("<div id=\"fg3\"></div>");
		html.append("<div id=\"fg4\"></div>");
		html.append("</div>");
		html.append("<div id=blue>");
		html.append("<div id=\"fb1\"></div>");
		html.append("<div id=\"fb2\"></div>");
		html.append("<div id=\"fb3\"></div>");
		html.append("<div id=\"fb4\"></div>");
		html.append("</div>");
		html.append("<div id=yellow>");
		html.append("<div id=\"fy1\"></div>");
		html.append("<div id=\"fy2\"></div>");
		html.append("<div id=\"fy3\"></div>");
		html.append("<div id=\"fy4\"></div>");
		html.append("</div>");
		html.append("<div id=\"f01\"></div>");
		html.append("<div id=\"f02\"></div>");
		html.append("<div id=\"f03\"></div>");
		html.append("<div id=\"f04\"></div>");
		html.append("<div id=\"f05\"></div>");
		html.append("<div id=\"f06\"></div>");
		html.append("<div id=\"f07\"></div>");
		html.append("<div id=\"f08\"></div>");
		html.append("<div id=\"f09\"></div>");
		html.append("<div id=\"f10\"></div>");
		html.append("<div id=\"f11\"></div>");
		html.append("<div id=\"f12\"></div>");
		html.append("<div id=\"f13\"></div>");
		html.append("<div id=\"f14\"></div>");
		html.append("<div id=\"f15\"></div>");
		html.append("<div id=\"f16\"></div>");
		html.append("<div id=\"f17\"></div>");
		html.append("<div id=\"f18\"></div>");
		html.append("<div id=\"f19\"></div>");
		html.append("<div id=\"f20\"></div>");
		html.append("<div id=\"f21\"></div>");
		html.append("<div id=\"f22\"></div>");
		html.append("<div id=\"f23\"></div>");
		html.append("<div id=\"f24\"></div>");
		html.append("<div id=\"f25\"></div>");
		html.append("<div id=\"f26\"></div>");
		html.append("<div id=\"f27\"></div>");
		html.append("<div id=\"f28\"></div>");
		html.append("<div id=\"f29\"></div>");
		html.append("<div id=\"f30\"></div>");
		html.append("<div id=\"f31\"></div>");
		html.append("<div id=\"f32\"></div>");
		html.append("<div id=\"f33\"></div>");
		html.append("<div id=\"f34\"></div>");
		html.append("<div id=\"f35\"></div>");
		html.append("<div id=\"f36\"></div>");
		html.append("<div id=\"f37\"></div>");
		html.append("<div id=\"f39\"></div>");
		html.append("<div id=\"f40\"></div>");
		html.append("<div id=\"f41\"></div>");
		html.append("<div id=\"f42\"></div>");
		html.append("<div id=\"f43\"></div>");
		html.append("<div id=\"f44\"></div>");
		html.append("<div id=\"f45\"></div>");
		html.append("<div id=\"f46\"></div>");
		html.append("<div id=\"f47\"></div>");
		html.append("<div id=\"f48\"></div>");
		html.append("<div id=\"f49\"></div>");
		html.append("<div id=\"f50\"></div>");
		html.append("<div id=\"f51\"></div>");
		html.append("<div id=\"f52\"></div>");
		
		html.append("</div>");
		html_board = html.toString();
		
		return html.toString();
	}
	
	public String MoveRedChipTo(String id, boolean init){
		int chipCount=0;
		html_board = html.toString();
		int fstelle = 0;
		for(int i=0;i<html_board.length();i++){
			char c 		=  html_board.charAt(i);
			String s 	= Character.toString(c);
			if(init){
				if (s.equals("f")){
					c 		=  html_board.charAt(i+1);
					s 	= Character.toString(c);
						if (s.equals("r")){
							c 		=  html_board.charAt(i+2);
							s 		= Character.toString(c);
							if (s.equals(id)){
								fstelle = i;
							}
						}
				}
			}
			else{
				if (s.equals("f")){
					c 		=  html_board.charAt(i+1);
					s 	= Character.toString(c);
					if (s.equals(id.substring(0, 1))){
						c 		=  html_board.charAt(i+2);
						s 	= Character.toString(c);
						if (s.equals(id.substring(1, 2))){
							fstelle = i;
						}
					}
				}
			}
		}
		html.insert(fstelle+5, "<img src=\"rchip.png\" width=\"35\" height=\"35\">");
		return html.toString();
	}
	
	public String MoveGreenChipTo(String id,boolean init){
		
		String html_board = html.toString();
		int fstelle = 0;
		for(int i=0;i<html_board.length();i++){
			char c 		=  html_board.charAt(i);
			String s 	= Character.toString(c);
			if(init){
				if (s.equals("f")){
					c 		=  html_board.charAt(i+1);
					s 	= Character.toString(c);
						if (s.equals("g")){
							c 		=  html_board.charAt(i+2);
							s 		= Character.toString(c);
							if (s.equals(id)){
								fstelle = i;
							}
						}
				}
			}
			else{
				if (s.equals("f")){
					c 		=  html_board.charAt(i+1);
					s 	= Character.toString(c);
					if (s.equals(id.substring(0, 1))){
						c 		=  html_board.charAt(i+2);
						s 	= Character.toString(c);
						if (s.equals(id.substring(1, 2))){
							fstelle = i;
						}
					}
				}
			}
		}
		html.insert(fstelle+5, "<img src=\"gchip.png\" width=\"35\" height=\"35\">");
		return html.toString();
	}
	
	public String MoveBlueChipTo(String id, boolean init){

		String html_board = html.toString();

		int fstelle = 0;
		for(int i=0;i<html_board.length();i++){
			char c 		=  html_board.charAt(i);
			String s 	= Character.toString(c);
			if(init){
				if (s.equals("f")){
					c 		=  html_board.charAt(i+1);
					s 	= Character.toString(c);
						if (s.equals("b")){
							c 		=  html_board.charAt(i+2);
							s 		= Character.toString(c);
							if (s.equals(id)){
								fstelle = i;
							}
						}
				}
			}
			else{
				if (s.equals("f")){
					c 		=  html_board.charAt(i+1);
					s 	= Character.toString(c);
					if (s.equals(id.substring(0, 1))){
						c 		=  html_board.charAt(i+2);
						s 	= Character.toString(c);
						if (s.equals(id.substring(1, 2))){
							fstelle = i;
						}
					}
				}
			}
		}
		html.insert(fstelle+5, "<img src=\"bchip.png\" width=\"35\" height=\"35\">");
		return html.toString();
	}
	
	public String MoveYellowChipTo(String id, boolean init){

		String html_board = html.toString();

		int fstelle = 0;
		for(int i=0;i<html_board.length();i++){
			char c 		=  html_board.charAt(i);
			String s 	= Character.toString(c);
			if(init){
				if (s.equals("f")){
					c 		=  html_board.charAt(i+1);
					s 	= Character.toString(c);
						if (s.equals("y")){
							c 		=  html_board.charAt(i+2);
							s 		= Character.toString(c);
							if (s.equals(id)){
								fstelle = i;
							}
						}
				}
			}
			else{
				if (s.equals("f")){
					c 		=  html_board.charAt(i+1);
					s 	= Character.toString(c);
					if (s.equals(id.substring(0, 1))){
						c 		=  html_board.charAt(i+2);
						s 	= Character.toString(c);
						if (s.equals(id.substring(1, 2))){
							fstelle = i;
						}
					}
				}
			}
		}
		html.insert(fstelle+5, "<img src=\"ychip.png\" width=\"35\" height=\"35\">");
		return html.toString();
	}*/
	

}
