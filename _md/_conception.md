# Create an application to manage a Gas Station

## OUTPUT
1 - Money in/out
2 - Quantity of essence in Stock
3 - Profit
4 - Situation de dette et de créances 

## INPUT
- Money Counter 
- Date 
- Pompist (select)
- Pompe (select)

## FUNCTIONNALITY
- Consult a table with inflow and outflow of money
    + Ecriture comptable
    + Notion of `ecriture`, `classe` ...

- Pompist session
    + 1 Pompist = 1 Pompe ? YES
    + 1 Pompe = 1 Cuve ? YES
    + Example of a Pompist session
        -> Pompist A | Pompe A | 24-09-2024 | 1000$
        -> Pompist B | Pompe A | 25-09-2024 | 1200$
        -> Pompist C | Pompe A | 25-09-2024 | 1650$
        -> Pompist D | Pompe B | 24-09-2024 | 900$
        -> Pompist A | Pompe B | 24-09-2024 | 1200$    

- Consult the stock of essence
    + The Station has n `Cuve` of essence
        + e.g: 5 Cuves of 2000L 
    + Consult the stock of essence in a Cuve based on the value of the Counter
    + e.g: 
        + PU = 2$ (Unit => Liters)
        + Initial value of essence Stock = 2000L
        + Initial Counter = 100$
        + Counter Value at the end of a Pompist Service = 450$
            => Consumed stock of essence = initial money counter value / PU
            => Current stock of essence = Initial value of essence - Consumed stock of essence    
    + The stock value can change due to two factors : 
        + `Achat Essence` from `fournisseur`
            => Stock essence++
            => Money--
        + `Vente Essence` from `client`
            => Stock essence--
            => Money++
    + All of the changes need to be registered with a Facture (`ecriture comptable`)
        + Create an ecriture for each transaction
    + Define `PU d'achat` and `PU de vente`
    + Facture of `fournisseur`
    + Etat de Stock per Cuve
    + Etat de `Caisse`

- Can check the profit based on date parameters
    + Date parameters default value : Date.now() 
    + Notion of `classe 7` and `classe 6`
    + Profit of the whole Magasin

- Situation of `Dette` and `Créances`
    + Notion `Dette` & `Créances`