package learn.spooky.models;

public enum EncounterType {
    VISUAL(1),
    AUDITORY(2),
    TOUCH(3),
    TEMPERATURE(4);

    private int typeId;

    private EncounterType(int typeId){
        this.typeId = typeId;
    }

    public EncounterType valueOfType(int typeId) {
        for(EncounterType a : EncounterType.values()){
            if(a.getTypeId() == typeId){
                return a;
            }
        }
        return null;
    }

    public int getTypeId() {
        return typeId;
    }


}
