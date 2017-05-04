package classes_suporte;


public class Const {
	//================= CTE DE INICIO DE BUSCA==========
	public static final int DARTH_VADER = 1;
	public static final int LEIA = 2;
	public static final int PRETO = 1;
	public static final int BRANCO = 2;
	public static final int VERMELHO = 11;
	public static final int VERDE = 12;
	public static final int ESQUERDA = 3;
	public static final int DIREITA = 4;
	
	public static final float US_MIN = 0.03f;
	public static final float US_MAX = 0.2f;

	public static final double ACC = 0.5; //m/s^2
	public static final double VELOCIDADE_E = 0.2; //m/s
	public static final double VELOCIDADE_D = 0.2049; //m/s

	public static final double RAIO_RODA =  0.0401;
	public static double RAIO_ROBO = 0.0646;//0.0647(semboneco) 0.065(comboneco)
	//TODO alterar o valor do raio do robo quando este pegar um boneco
	public static final double ESPACO_DE_ACC = VELOCIDADE_E*VELOCIDADE_E/(2*ACC); // espaco que a aceleracao/desaceleracao dura
	
	public static final double Kp = 1;
	public static final double Kd = 1;
	public static final int dt = 5; // tempo do PD em ms
	
	public static final int SENSOR_COR = 8;
	public static final int SENSOR_US = 9;
}
