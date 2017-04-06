package com.romain.louis.weatherapp;

/**
 * Created by romai on 23/03/2017.
 */

public class TraductTemps {
    public TraductTemps(){

    }

    public static String getDescTemps(int idTemps) {
        String frTrad;

        switch (idTemps) {
            case 200:
                frTrad = "Orage avec pluie fine";
                break;
            case 201:
                frTrad = "Orage avec pluie";
                break;
            case 202:
                frTrad = "Orage avec pluie epaisse";
                break;
            case 210:
                frTrad = "Orageux";
                break;
            case 211:
                frTrad = "Orage";
                break;
            case 212:
                frTrad = "Gros orage";
                break;
            case 221:
                frTrad = "Orage déchiré";
                break;
            case 230:
                frTrad = "Orage avec risque d'eclairs";
                break;
            case 231:
                frTrad = "Orage avec eclairs";
                break;
            case 232:
                frTrad = "Orage avec de gros éclairs";
                break;
            case 300:
                frTrad = "Fine bruine";
                break;
            case 301:
                frTrad = "Bruine";
                break;
            case 302:
                frTrad = "Bruine epaisse";
                break;
            case 310:
                frTrad = "Bruine pluvieuse";
                break;
            case 311:
                frTrad = "Bruine epaisse, risque de pluie";
                break;
            case 312:
                frTrad = "Bruine très épaisse";
                break;
            case 313:
                frTrad = "Pluvieux";
                break;
            case 314:
                frTrad = "Pluie forte";
                break;
            case 321:
                frTrad = "Bruine très épaisse";
                break;
            case 500:
                frTrad = "Pluvieux";
                break;
            case 501:
                frTrad = "Pluie fine";
                break;
            case 502:
                frTrad = "Pluie epaisse";
                break;
            case 503:
                frTrad = "Pluie très épaisse";
                break;
            case 504:
                frTrad = "Pluie très epaisse";
                break;
            case 511:
                frTrad = "Pluie verglassante";
                break;
            case 520:
                frTrad = "Pluie très fine";
                break;
            case 521:
                frTrad = "Pluie fine";
                break;
            case 522:
                frTrad = "Pluie fine";
                break;
            case 531:
                frTrad = "Puie fine";
                break;
            case 600:
                frTrad = "Neigeux";
                break;
            case 601:
                frTrad = "Neige";
                break;
            case 602:
                frTrad = "Neige epaisse";
                break;
            case 611:
                frTrad = "Neige fondue";
                break;
            case 612:
                frTrad = "Neige fondue";
                break;
            case 615:
                frTrad = "Pluie fine et Neige";
                break;
            case 616:
                frTrad = "Pluie accompagnée de Neige";
                break;
            case 620:
                frTrad = "Neige fine";
                break;
            case 621:
                frTrad = "Neige fine";
                break;
            case 622:
                frTrad = "Neige fine";
                break;
            case 701:
                frTrad = "Brouillardeux";
                break;
            case 711:
                frTrad = "Enfumé";
                break;
            case 721:
                frTrad = "Brumeux";
                break;
            case 731:
                frTrad = "Vents de poussière";
                break;
            case 741:
                frTrad = "Brouillard";
                break;
            case 751:
                frTrad = "Pluie de sable";
                break;
            case 761:
                frTrad = "Poussiereux";
                break;
            case 762:
                frTrad = "Cendres volcaniques";
                break;
            case 771:
                frTrad = "Bourrasques de vent";
                break;
            case 781:
                frTrad = "Risque de tornade";
                break;
            case 800:
                frTrad = "Temps clair";
                break;
            case 801:
                frTrad = "Quelques nuages";
                break;
            case 802:
                frTrad = "Nuageux";
                break;
            case 803:
                frTrad = "Nuages";
                break;
            case 804:
                frTrad = "Ciel voilé";
                break;
            case 900:
                frTrad = "Tornade";
                break;
            case 901:
                frTrad = "Tempête tropicale";
                break;
            case 902:
                frTrad = "Ouragan";
                break;
            case 903:
                frTrad = "Froid";
                break;
            case 904:
                frTrad = "Chaud";
                break;
            case 905:
                frTrad = "Venteux";
                break;
            case 906:
                frTrad = "Grêle";
                break;
            case 951:
                frTrad = "Temps calme";
                break;
            case 952:
                frTrad = "Petite brise";
                break;
            case 953:
                frTrad = "Brise";
                break;
            case 954:
                frTrad = "Brise";
                break;
            case 955:
                frTrad = "Brise importante";
                break;
            case 956:
                frTrad = "Ventes violents";
                break;
            case 957:
                frTrad = "Coups de vents";
                break;
            case 958:
                frTrad = "Vents violents";
                break;
            case 959:
                frTrad = "Tempête";
                break;
            case 960:
                frTrad = "Tempête violente";
                break;
            case 961:
                frTrad = "Tempête violente";
                break;
            case 962:
                frTrad = "Ouragan";
                break;
            default:
                frTrad = "Error";
                break;
        }
        return frTrad;
    }
}

