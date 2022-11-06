package br.ufmg.engsoft.urna.services.interfaces;

public interface IHandler<TInput,TOutput> {
	
	TOutput handle(TInput input);
	
}
