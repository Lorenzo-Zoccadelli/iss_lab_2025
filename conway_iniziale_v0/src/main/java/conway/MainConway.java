package conway;

import conway.device.ConwayInputMock;

public class MainConway  {

    public static void main(String[] args) {

      //configureTheSystem
        Life life           =
                new Life( 3,3 );
        LifeController cc   =
                new LifeController(life, 5);
        ConwayInputMock cim =
                new ConwayInputMock(cc,life);

      //start the system
        cim.simulateUserControl();

    }
}