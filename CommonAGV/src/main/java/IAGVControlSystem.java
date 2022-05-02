public interface IAGVControlSystem {

void loadProgram(String program, String state);

String getStatus();

void batteryCheck();
}
