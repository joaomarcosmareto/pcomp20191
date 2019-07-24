# -*- coding: utf-8 -*-
def organiza(lista):

    temp_dict = {}
    aux_lista = []

    for registro in lista:
        temp_dict['_id'] = registro['_id']
        temp_dict['g_plus'] = {
            'Gid'            : registro['Gid'],
            'G_Firstname'    : registro['G_Firstname'],
            'G_Location'     : registro['G_Location'],
            'G_Lastname'     : registro['G_Lastname'],
            'G_aboutme'      : registro['G_aboutme'],
            'G_Displayname'  : registro['G_Displayname'],
        }
        temp_dict['twitter'] = {
            'Tid'           : registro['Tid'],
            'T_Fullname'    : registro['T_Fullname'],
            'T_Location'    : registro['T_Location'],
            'T_Time_Zone'   : registro['T_Time_Zone'],
            'T_StatusText'  : registro['T_StatusText'],
            'T_ScreenName'  : registro['T_ScreenName'],
            'T_Description' : registro['T_Description'],
            'T_Language'    : registro['T_Language'],
        }

        aux_lista.append(temp_dict)
        temp_dict = {}

    return aux_lista

def build_negativos_dificuldade_1(lista):
    aux_lista = []

    len_lista = len(lista)

    temp_dict = {
        '_id'     : 'F_0',
        'g_plus'  : lista[0]['g_plus'],
        'twitter' : lista[-1]['twitter']
    }

    aux_lista.append(temp_dict)

    for i in range(1, len_lista):

        j = i + 1

        temp_dict = {
            '_id'     : 'F_' + str(i),
            'g_plus'  : lista[i]['g_plus'],
            'twitter' : lista[-j]['twitter']
        }

        aux_lista.append(temp_dict)

    return aux_lista