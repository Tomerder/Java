package poker;

import java.util.Vector;

public class Player {
	
	enum Action { Fold , Call, Check , Raise , ReRaise , AllIn } ;
	
	String _name;
	int _numOfChips;
	//History _history;
	Boolean _isInTheHand;
	
	Action _lastAction ;
	Vector<Action> _actionsInHand;
	
	
	public String get_name() {
		return _name;
	}
	public void set_name(String _name) {
		this._name = _name;
	}
	public int get_numOfChips() {
		return _numOfChips;
	}
	public void set_numOfChips(int ofChips) {
		_numOfChips = ofChips;
	}
	public Boolean getIsInTheHand() {
		return _isInTheHand;
	}
	public void setIsInTheHand(Boolean isInTheHand) {
		this._isInTheHand = isInTheHand;
	}
	public Action get_lastAction() {
		return _lastAction;
	}
	public void set_lastAction(Action action) {
		_lastAction = action;
	}
	public Vector<Action> get_actionsInHand() {
		return _actionsInHand;
	}
	public void set_actionsInHand(Vector<Action> inHand) {
		_actionsInHand = inHand;
	}
	

}
