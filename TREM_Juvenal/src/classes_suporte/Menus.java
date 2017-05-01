package classes_suporte;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;

public class Menus {
	private boolean bossJaEscolhido = false;
	private int boss = Const.DARTH_VADER;
	private int ladoDeProcura = Const.ESQUERDA;

	private void mostraMenuLightOrDarkSide(int corDoBoss) {
		LCD.clear();
		switch(corDoBoss){
		case 1:{
			LCD.drawString("RESGATAR QUAL BOSS?", 0, 0);
			LCD.drawString("<PRETO - DARTK>", 0, 1);
			break;
		}
		case 2:{
			LCD.drawString("RESGATAR QUAL BOSS?", 0, 0);
			LCD.drawString("<BRANCO - LEIA>", 0, 1);
			break;
		}
		}
	}
	
	private void mostraMenuIniciarPorOnde(int ladoDoDeProcura) {
		LCD.clear();
		switch(ladoDoDeProcura){
		case 1:{
			LCD.drawString("RESGATAR QUAL BOSS?", 0, 0);
			LCD.drawString("PRETO - DARTK", 0, 1);
			break;
		}
		case 2:{
			LCD.drawString("RESGATAR QUAL BOSS?", 0, 0);
			LCD.drawString("BRANCO - LEIA", 0, 1);
			break;
		}
		}
	}

	public void mostraMenus(){
		boolean escolhaRealizada = false;

		while (!escolhaRealizada && !bossJaEscolhido) {// ESCOLHER BOSS
			mostraMenuLightOrDarkSide(boss);
			switch (Button.waitForAnyPress()) {
			case Button.ID_ENTER: {
				LCD.clear();
				escolhaRealizada = true;
				bossJaEscolhido = true;
				break;
			}default : {
				if(boss == Const.DARTH_VADER) boss = Const.LEIA;
				else boss = Const.DARTH_VADER;
				break;
			}
			}
		}

		escolhaRealizada = false;

		while(!escolhaRealizada){
			mostraMenuIniciarPorOnde(ladoDeProcura);
			switch (Button.waitForAnyPress()) {// ESCOLHER LADO DE INICIO DE BUSCA
			case Button.ID_RIGHT: {
				switch(ladoDeProcura){
				case Const.ESQUERDA:{
					ladoDeProcura = Const.DIREITA;
					break;
				}case Const.DIREITA:{
					ladoDeProcura = Const.ESQUERDA;
					break;
				}
				}
				break;
			}
			case Button.ID_LEFT: {
				switch(ladoDeProcura){
				case Const.ESQUERDA:{
					ladoDeProcura = Const.DIREITA;
					break;
				}case Const.DIREITA:{
					ladoDeProcura = Const.ESQUERDA;
					break;
				}
				}
				break;
			}
			case Button.ID_ENTER: {
				LCD.clear();
				escolhaRealizada = true;
				break;
			}
			}
		}
	}
	
	public int getBoss(){
		return boss;
	}
	
	public int getLadoDeProcura(){
		return ladoDeProcura;
	}

}
