package tounzcompany.light_controler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.philips.lighting.model.PHLight;

import java.util.ArrayList;

import tounzcompany.light_controler.R;


public class AdapterLights extends ArrayAdapter<PHLight>{
    private ArrayList<PHLight> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView textIconLights;

    }

    public AdapterLights(ArrayList<PHLight> data, Context context) {
        super(context, R.layout.fragment_list_lights, data);
        this.dataSet = data;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        // Check if an existing view is being reused, otherwise inflate the view
        final ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.fragment_list_lights, parent, false);
            viewHolder.textIconLights = (TextView) convertView.findViewById(R.id.text_icon_lights);



            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.textIconLights.setText(dataSet.get(position).getName());



        // Return the completed view to render on screen
        return convertView;
    }

}
