package br.ufmg.engsoft.reprova.services.interfaces;

public interface IHandler<TInput,TOutput> {
	
	TOutput handle(TInput input);
	
}
