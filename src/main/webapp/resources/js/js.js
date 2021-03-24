function confirmer() {
    return confirm( 'Etes vous sûr de vouloir supprimer? ' );
}

function DisabledConfirm() {
    return confirm( ' Voulez-vous vraiment désactiver l\'annonce ?' );
}

function enabledConfirm() {
    return confirm( ' Voulez-vous vraiment réactiver ?' );
}

function regExPass() {
    alert( 'je suis dedans' );
}

$( document ).ready( function () {
    $( '.dataUser' ).DataTable( {
        retrieve: true,
        orderFixed: [[2, 'desc'], [0, 'asc']],
        rowGroup: {
            dataSrc: 2
        }
    } );
} );

new Splide( '.splide' ).mount();
