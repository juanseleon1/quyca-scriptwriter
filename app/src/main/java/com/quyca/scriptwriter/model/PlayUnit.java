package com.quyca.scriptwriter.model;

import com.google.gson.annotations.Expose;
import com.quyca.robotmanager.net.Arc;
import com.quyca.robotmanager.net.PetriNet;
import com.quyca.robotmanager.net.Place;
import com.quyca.robotmanager.net.Transition;
import com.quyca.robotmanager.net.builders.ArcBuilder;
import com.quyca.robotmanager.net.builders.TransitionBuilder;
import com.quyca.robotmanager.network.RobotExecutioner;
import com.quyca.scriptwriter.R;
import com.quyca.scriptwriter.integ.network.QuycaMessageTransformer;
import com.quyca.scriptwriter.integ.utils.NetBundle;
import com.quyca.scriptwriter.integ.utils.UIBundle;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class PlayUnit extends Playable implements Serializable {

    @Expose
    protected List<Playable> playables;

    @Override
    public NetBundle play(Map<String, QuycaMessageTransformer> msgCreators, Map<String, RobotExecutioner> senders, PetriNet net, UIBundle bundle) {
        NetBundle netBundle = new NetBundle();
        playables.forEach(playable -> {
            NetBundle actBundle = playable.play(msgCreators, senders, net, bundle);
            List<Place> top = actBundle.getTopPlaces();
            List<Place> bottom = actBundle.getBottomPlaces();
            netBundle.getBottomPlaces().addAll(bottom);
            netBundle.getTopPlaces().addAll(top);
        });

        return netBundle;
    }

    public List<Playable> getPlayables() {
        return playables;
    }

    public void setPlayables(List<Playable> playables) {
        this.playables = playables;
    }

    public Playable getPlayable(int i){
        return playables.get(i);
    }
}







